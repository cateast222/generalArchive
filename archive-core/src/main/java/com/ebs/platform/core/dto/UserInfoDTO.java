package com.ebs.platform.core.dto;

/**
 * 用户信息
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:28
 */
public class UserInfoDTO {
    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", entPersonnelId='" + entPersonnelId + '\'' +
                ", entPersonnelName='" + entPersonnelName + '\'' +
                ", entId='" + entId + '\'' +
                ", entName='" + entName + '\'' +
                ", entDeptId='" + entDeptId + '\'' +
                ", entDeptName='" + entDeptName + '\'' +
                ", appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", appMainUrl='" + appMainUrl + '\'' +
                '}';
    }
    private String userId;
    private String username;
    private String tenantId;
    private String tenantName;
    private String entPersonnelId;
    private String entPersonnelName;
    private String entId;
    private String entName;
    private String entDeptId;
    private String entDeptName;
    private String appId;
    private String appName;
    private String appMainUrl;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getEntPersonnelId() {
        return entPersonnelId;
    }

    public void setEntPersonnelId(String entPersonnelId) {
        this.entPersonnelId = entPersonnelId;
    }

    public String getEntPersonnelName() {
        return entPersonnelName;
    }

    public void setEntPersonnelName(String entPersonnelName) {
        this.entPersonnelName = entPersonnelName;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getEntDeptId() {
        return entDeptId;
    }

    public void setEntDeptId(String entDeptId) {
        this.entDeptId = entDeptId;
    }

    public String getEntDeptName() {
        return entDeptName;
    }

    public void setEntDeptName(String entDeptName) {
        this.entDeptName = entDeptName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppMainUrl() {
        return appMainUrl;
    }

    public void setAppMainUrl(String appMainUrl) {
        this.appMainUrl = appMainUrl;
    }
}
