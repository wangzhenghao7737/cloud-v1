package com.xiaosa.clouddemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosa.clouddemo.constant.UserConstant;
import com.xiaosa.clouddemo.domain.User;
import com.xiaosa.clouddemo.dto.user.admin.SelectUserListDto;
import com.xiaosa.clouddemo.mapper.UserMapper;
import com.xiaosa.clouddemo.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserAdmin {
    @Resource
    private UserMapper userMapper;

    @Test
    public void ListUser(){
        SelectUserListDto condition = new SelectUserListDto();
        condition.setExist(true);
        condition.setPage(1);
        condition.setPageSize(3);
        condition.setStatus(0);
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
        System.out.println(users);
    }
}
