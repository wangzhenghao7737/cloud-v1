package com.xiaosa.clouddemo.controller;

import com.xiaosa.clouddemo.result.ApiResponse;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/identity")
public class TestController {

    @Resource
    private PasswordEncoder passwordEncoder;


    //匿名访问
    @GetMapping("/test1")
    public ApiResponse<String>  getTest(){
        return ApiResponse.success("匿名");
    }
    //认证后访问
    @GetMapping("/test2")
    public ApiResponse<String> getTest2(){
        return ApiResponse.success("已认证");
    }
    //认证+admin角色访问
    @PreAuthorize(value = "hasRole('admin')")
    @GetMapping("/test3")
    public ApiResponse<String> getTest3(){
        return ApiResponse.success("已admin");
    }
    //认证+cto||cfo角色访问
    @GetMapping("/test4")
    public ApiResponse<String> getTest4(){
        return ApiResponse.success("认证+cto||cfo角色访问");
    }
    //认证+cto&&ceo角色访问
    @GetMapping("/test5")
    public ApiResponse<String> getTest5(){
        return ApiResponse.success("认证+cto&&cfo角色访问");
    }
    //认证+del权限访问
    @GetMapping("/test6")
    public ApiResponse<String> getTest6(){
        return ApiResponse.success("认证+cto&&cfo角色访问");
    }
    //认证+del||edit权限访问
    @GetMapping("/test7")
    public ApiResponse<String> getTest7(){
        return ApiResponse.success("认证+cto&&cfo角色访问");
    }
    //认证+del**edit权限访问
    @GetMapping("/test8")
    public ApiResponse<String> getTest8(){
        return ApiResponse.success("认证+cto&&cfo角色访问");
    }
}
