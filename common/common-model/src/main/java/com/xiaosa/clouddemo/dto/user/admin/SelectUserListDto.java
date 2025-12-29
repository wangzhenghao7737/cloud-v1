package com.xiaosa.clouddemo.dto.user.admin;

import com.xiaosa.clouddemo.dto.user.admin.group.ageStatus;
import com.xiaosa.clouddemo.dto.user.admin.group.deleteStatus;
import com.xiaosa.clouddemo.dto.user.admin.group.nameDeleteStatus;
import com.xiaosa.clouddemo.dto.user.admin.group.scoreStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SelectUserListDto {
    @NotNull(groups = {ageStatus.class,
            deleteStatus.class,
            nameDeleteStatus.class,
            scoreStatus.class},message = "页码不能为空")
    @Min(groups = {ageStatus.class,
            deleteStatus.class,
            nameDeleteStatus.class,
            scoreStatus.class},value = 1, message = "页码必须大于0")
    private int page;
    @NotNull(groups = {ageStatus.class,
            deleteStatus.class,
            nameDeleteStatus.class,
            scoreStatus.class},message = "页大小不能为空")
    @Min(groups = {ageStatus.class,
            deleteStatus.class,
            nameDeleteStatus.class,
            scoreStatus.class},value = 1, message = "页大小必须大于0")
    private int pageSize;

    @Min(value = 0, groups = {
            deleteStatus.class,
            nameDeleteStatus.class},message = "用户状态不能为空")
    private int status;

    @NotNull(groups = {deleteStatus.class},message = "必须选择用户是否存在")
    private boolean exist;
    private int sex;

    @NotNull(groups = {ageStatus.class},message = "必须选择用户名是否存在")
    @Min(groups = {ageStatus.class},value = 0, message = "最小年龄不能小于0")
    private int minAge;

    @NotNull(groups = {ageStatus.class},message = "必须选择最大年龄")
    @Max(groups = {ageStatus.class},value = 999, message = "最大年龄不能大于999")
    private int maxAge;

    @NotNull(groups = {ageStatus.class},message = "必须选择年龄排序方式")
    private boolean ageAsc;

    @NotNull(groups = {scoreStatus.class},message = "必须选择用户名是否存在")
    @Min(groups = {scoreStatus.class},value = 0, message = "最小分数不能小于0")
    private Long minScore;

    private Long maxScore;

    @NotNull(groups = {scoreStatus.class},message = "必须选择升降序")
    private boolean scoreAsc;

    @NotEmpty(groups = {nameDeleteStatus.class},message = "必须选择姓")
    private String firstName;
    private String lastName;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public boolean getAgeAsc() {
        return ageAsc;
    }

    public void setAgeAsc(boolean ageAsc) {
        this.ageAsc = ageAsc;
    }

    public Long getMinScore() {
        return minScore;
    }

    public void setMinScore(Long minScore) {
        this.minScore = minScore;
    }

    public Long getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Long maxScore) {
        this.maxScore = maxScore;
    }

    public boolean getScoreAsc() {
        return scoreAsc;
    }

    public void setScoreAsc(boolean scoreAsc) {
        this.scoreAsc = scoreAsc;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
