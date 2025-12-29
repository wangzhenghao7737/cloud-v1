package com.xiaosa.clouddemo.mapper;

import com.xiaosa.clouddemo.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author D14
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-12-20 22:03:40
* @Entity com.xiaosa.clouddemo.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    User selectUserByIdWithLogicalDelete(@Param("id") Long id, @Param("isLogicalDelete") int isLogicalDelete);
}




