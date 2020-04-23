package com.ebs.platform.core.old;

import java.util.Date;

/**
 * 用户所属的当前租户信息
 */
public class UserTenantDTO  {

    private String appId;

    private String appName;

    private String appAdminUserId;

    private String appAdminEmail;

    private String tenantId;

    private String tenantName;

    private Date tenantExpiration;

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

    public String getAppAdminUserId() {
        return appAdminUserId;
    }

    public void setAppAdminUserId(String appAdminUserId) {
        this.appAdminUserId = appAdminUserId;
    }

    public String getAppAdminEmail() {
        return appAdminEmail;
    }

    public void setAppAdminEmail(String appAdminEmail) {
        this.appAdminEmail = appAdminEmail;
    }

    public Date getTenantExpiration() {
        return tenantExpiration;
    }

    public void setTenantExpiration(Date tenantExpiration) {
        this.tenantExpiration = tenantExpiration;
    }

    @Override
    public String toString() {
        return "UserTenantDTO{" +
                "tenantId='" + tenantId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", appAdminUserId='" + appAdminUserId + '\'' +
                ", appAdminEmail='" + appAdminEmail + '\'' +
                '}';
    }
}
