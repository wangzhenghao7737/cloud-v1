package com.xiaosa.clouddemo.service;

import com.xiaosa.clouddemo.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaosa.clouddemo.dto.user.ReduceMoney;
import com.xiaosa.clouddemo.dto.user.admin.SelectUserListDto;
import com.xiaosa.clouddemo.dto.user.admin.UserTemplateDto;
import com.xiaosa.clouddemo.entity.UserCommon;

import java.util.List;

/**
* @author D14
* @description 针对表【user】的数据库操作Service
* @createDate 2025-12-20 22:03:40
*/
public interface UserService extends IService<User> {
    UserCommon getUserCommonById(Long id);
    boolean reduceUserMoney(ReduceMoney reduceMoney);
    UserCommon updateUserCommon(UserCommon userCommon);
    List<UserTemplateDto> selectUserList(SelectUserListDto selectUserListDto);
    List<UserTemplateDto> selectPureUserFirstName(SelectUserListDto selectUserListDto);
    List<UserTemplateDto> selectAge(SelectUserListDto selectUserListDto);
    List<UserTemplateDto> selectScore(SelectUserListDto selectUserListDto);
}
