package com.xiaosa.clouddemo.mapper;

import com.xiaosa.clouddemo.entity.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author D14
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-12-25 11:13:33
* @Entity generator.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    User selectByUserId(Long id);
    User selectByUserPhone(String phone);
    User selectByPhoneWithPwd(String phone, String password);
}




