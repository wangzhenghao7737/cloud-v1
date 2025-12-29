package com.xiaosa.clouddemo.controller;

import com.xiaosa.clouddemo.domain.User;
import com.xiaosa.clouddemo.dto.user.admin.SelectUserListDto;
import com.xiaosa.clouddemo.dto.user.admin.UserTemplateDto;
import com.xiaosa.clouddemo.dto.user.admin.group.ageStatus;
import com.xiaosa.clouddemo.dto.user.admin.group.deleteStatus;
import com.xiaosa.clouddemo.dto.user.admin.group.nameDeleteStatus;
import com.xiaosa.clouddemo.dto.user.admin.group.scoreStatus;
import com.xiaosa.clouddemo.result.ApiResponse;
import com.xiaosa.clouddemo.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.xiaosa.clouddemo.utils.GenerateUser;

/**
 * status,deleted,id联合索引查询
 * name,id索引。前缀模糊匹配
 * age,sore,id索引。范围查询
 */
@Slf4j
@RestController
@RequestMapping("/user/admin")
public class UserAdminController {
    @Resource
    private UserService userService;

    @Resource
    @Qualifier("userServiceScheduledPool")
    private ScheduledThreadPoolExecutor scheduledPoolExecutor;
    private ScheduledFuture<?> scheduledFuture;

    /**
     * 用户列表
     * 根据status,deleted, score或id升降序 查询
     * @param selectUserListDto
     *
     */
    @PostMapping("/selectUserList")
    public ApiResponse<List<UserTemplateDto>> selectUserList(@Validated({deleteStatus.class}) @RequestBody SelectUserListDto selectUserListDto) {
        final List<UserTemplateDto> userList = userService.selectUserList(selectUserListDto);
        return ApiResponse.success(userList);
    }
    @PostMapping("/selectFirstName")
    public ApiResponse<List<UserTemplateDto>> selectFirstName(@Validated({nameDeleteStatus.class}) @RequestBody SelectUserListDto selectUserListDto) {
        log.info(selectUserListDto.toString());
        final List<UserTemplateDto> userList = userService.selectPureUserFirstName(selectUserListDto);
        for (UserTemplateDto user : userList) {
            log.info(user.toString());
        }
        return ApiResponse.success(userList);
    }
    @PostMapping("/selectAge")
    public ApiResponse<List<UserTemplateDto>> selectAge(@Validated({ageStatus.class}) @RequestBody SelectUserListDto selectUserListDto) {
        final List<UserTemplateDto> userList = userService.selectAge(selectUserListDto);
        return ApiResponse.success(userList);
    }
    @PostMapping("/selectScore")
    public ApiResponse<List<UserTemplateDto>> selectScore(@Validated({scoreStatus.class}) @RequestBody SelectUserListDto selectUserListDto) {
        final List<UserTemplateDto> userList = userService.selectScore(selectUserListDto);
        return ApiResponse.success(userList);
    }
    @PutMapping("/generate/{num}")
    public ApiResponse<Boolean> generate(@PathVariable("num") int num) {
        if(scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
        AtomicInteger i = new AtomicInteger(1);
        scheduledFuture = scheduledPoolExecutor.scheduleWithFixedDelay(() -> {
            List<User> users = GenerateUser.generateUsers(num);
            userService.saveBatch(users);
            log.info("insert batch {}", i.incrementAndGet());
        }, 5, 1, TimeUnit.SECONDS);
        return ApiResponse.success(true);
    }
    @GetMapping("/cancel")
    public ApiResponse<Boolean> cancel() {
        if(scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
        }
        return ApiResponse.success(true);
    }
}
