package com.xiaosa.clouddemo.entity;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;

public class UserCommon {
    /**
     *
     */
    @Positive
    private Long userId;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String account;

    /**
     *
     */
    private String userName;

    /**
     *
     */
    private Integer sex;

    /**
     *
     */
    private Integer userStatus;

    /**
     *
     */
    private Integer age;

    /**
     *
     */
    private BigDecimal money;

    /**
     *
     */
    private Long score;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Integer logicDelete;

    /**
     *
     */
    public Long getUserId() {
        return userId;
    }

    /**
     *
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     */
    public String getAccount() {
        return account;
    }

    /**
     *
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     *
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     */
    public Integer getSex() {
        return sex;
    }

    /**
     *
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     *
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     *
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     *
     */
    public Integer getAge() {
        return age;
    }

    /**
     *
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     *
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     *
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     *
     */
    public Long getScore() {
        return score;
    }

    /**
     *
     */
    public void setScore(Long score) {
        this.score = score;
    }

    /**
     *
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     *
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     */
    public Integer getLogicDelete() {
        return logicDelete;
    }

    /**
     *
     */
    public void setLogicDelete(Integer logicDelete) {
        this.logicDelete = logicDelete;
    }

}
