package com.xiaosa.clouddemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaosa.clouddemo.codeEnum.UserCodeEnum;
import com.xiaosa.clouddemo.constant.UserConstant;
import com.xiaosa.clouddemo.domain.User;
import com.xiaosa.clouddemo.dto.user.ReduceMoney;
import com.xiaosa.clouddemo.dto.user.admin.SelectUserListDto;
import com.xiaosa.clouddemo.dto.user.admin.UserTemplateDto;
import com.xiaosa.clouddemo.entity.UserCommon;
import com.xiaosa.clouddemo.exception.BusinessException;
import com.xiaosa.clouddemo.service.UserDtoMapper;
import com.xiaosa.clouddemo.service.UserService;
import com.xiaosa.clouddemo.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author D14
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-12-20 22:03:40
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private com.xiaosa.clouddemo.mapper.UserMapper userMapper;
    @Autowired
    private UserDtoMapper userDtoMapper;

    @Override
    public UserCommon getUserCommonById(Long id) {
        final User user = userMapper.selectUserByIdWithLogicalDelete(id, UserConstant.USER_UNDELETED);
        if(ObjectUtils.isEmpty(user)){
            throw new BusinessException(UserCodeEnum.USER_NOT_EXIST);
        }
        if(user.getUserStatus()==UserConstant.USER_FROZEN){
            throw new BusinessException(UserCodeEnum.USER_FROZEN);
        }
        return userDtoMapper.toCommon(user);
    }

    @Transactional
    @Override
    public boolean reduceUserMoney(ReduceMoney reduceMoney) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",reduceMoney.getUserId());
        queryWrapper.eq("logic_delete", UserConstant.USER_UNDELETED);
        queryWrapper.select("user_id","user_status","age","money");
        final User user = userMapper.selectOne(queryWrapper);
        if(ObjectUtils.isEmpty(user)){
            throw new BusinessException(UserCodeEnum.USER_NOT_EXIST);
        }
        if(user.getUserStatus()==UserConstant.USER_FROZEN){
            throw new BusinessException(UserCodeEnum.USER_FROZEN);
        }
        if(user.getMoney().compareTo(reduceMoney.getMoney())<0){
            throw new BusinessException(UserCodeEnum.USER_MONEY_NOT_ENOUGH);
        }
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",reduceMoney.getUserId());
        updateWrapper.set("money",user.getMoney().subtract(reduceMoney.getMoney()));
        final int result = userMapper.update(updateWrapper);
        if(result != UserConstant.UPDATE_SUCCESS){
            throw new BusinessException(UserCodeEnum.USER_UPDATE_ERROR);
        }
        return true;
    }

    @Override
    public UserCommon updateUserCommon(UserCommon userCommon) {
        return null;
    }

    @Override
    public List<UserTemplateDto> selectUserList(SelectUserListDto condition) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if(condition.getExist()){
            userQueryWrapper.eq("logic_delete", UserConstant.USER_UNDELETED);
        }else {
            userQueryWrapper.eq("logic_delete", UserConstant.USER_DELETED);
        }
        userQueryWrapper.eq("user_status", condition.getStatus());
        Page<User> page = new Page<>(condition.getPage(), condition.getPageSize());
        userMapper.selectPage(page, userQueryWrapper);
        List<User> users = page.getRecords();
        return users.stream()
                .filter(user-> ! ObjectUtils.isEmpty(user))
                .map(user->userDtoMapper.toTemplateDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserTemplateDto> selectPureUserFirstName(SelectUserListDto selectUserListDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_status", UserConstant.USER_NORMAL);
        queryWrapper.eq("logic_delete", UserConstant.USER_UNDELETED);
        queryWrapper.likeRight("user_name",selectUserListDto.getFirstName());
        Page<User> objectPage = new Page<>(selectUserListDto.getPage(), selectUserListDto.getPageSize());
        userMapper.selectPage(objectPage, queryWrapper);
        List<User> users = objectPage.getRecords();
        return users.stream()
                .filter(user-> ! ObjectUtils.isEmpty(user))
                .map(user->userDtoMapper.toTemplateDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserTemplateDto> selectAge(SelectUserListDto selectUserListDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_status", UserConstant.USER_NORMAL);
        queryWrapper.eq("logic_delete", UserConstant.USER_UNDELETED);
        queryWrapper.ge("age",selectUserListDto.getMinAge());
        queryWrapper.le("age",selectUserListDto.getMaxAge());
        if(selectUserListDto.getAgeAsc()){
            queryWrapper.orderByAsc("age");
        }else {
            queryWrapper.orderByDesc("age");
        }
        Page<User> objectPage = new Page<>();
        userMapper.selectPage(objectPage, queryWrapper);
        List<User> users = objectPage.getRecords();
        return users.stream()
                .filter(user-> !ObjectUtils.isEmpty(user))
                .map(user->userDtoMapper.toTemplateDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserTemplateDto> selectScore(SelectUserListDto selectUserListDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_status", UserConstant.USER_NORMAL);
        queryWrapper.eq("logic_delete", UserConstant.USER_UNDELETED);
        queryWrapper.ge("score",selectUserListDto.getMinScore());
        if(selectUserListDto.getScoreAsc()){
            queryWrapper.orderByAsc("score");
        }else {
            queryWrapper.orderByDesc("score");
        }
        Page<User> objectPage = new Page<>();
        userMapper.selectPage(objectPage, queryWrapper);
        List<User> users = objectPage.getRecords();
        return users.stream()
                .filter(user-> !ObjectUtils.isEmpty(user))
                .map(user->userDtoMapper.toTemplateDto(user))
                .collect(Collectors.toList());
    }
}




