package com.xiaosa.clouddemo.controller;

import com.xiaosa.clouddemo.dto.user.ReduceMoney;
import com.xiaosa.clouddemo.entity.UserCommon;
import com.xiaosa.clouddemo.exception.BusinessException;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import com.xiaosa.clouddemo.codeEnum.UserCodeEnum;

/**
 * UserCommon比较泛用
 * 接口要严格校验数据
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public ApiResponse<UserCommon> getUserCommonById(@PathVariable Long id) {
        if(id == null || id <= 0){
            throw new BusinessException(UserCodeEnum.USER_REQUEST_PARAM_ERROR);
        }
        return ApiResponse.success(userService.getUserCommonById(id));
    }
    @PutMapping("/update")
    public ApiResponse<Boolean> updateUserCommon(@Valid @RequestBody UserCommon userCommon) {
       return null;
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> deleteUserCommon(@PathVariable Long id) {
       return null;
    }
    @PutMapping("/reduceMoney")
    public ApiResponse<Boolean> reduceUserMoney(@Valid @RequestBody ReduceMoney userCommon) {
        if(ObjectUtils.isEmpty(userCommon)
            || ObjectUtils.isEmpty(userCommon.getUserId())
            || ObjectUtils.isEmpty(userCommon.getMoney())){
            throw new BusinessException(UserCodeEnum.USER_REQUEST_PARAM_ERROR);
        }
        return ApiResponse.success(userService.reduceUserMoney(userCommon));
    }
}
