package com.ebs.platform.core.dto;

import java.util.Date;

/**
 * 用户租赁的应用
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:38
 */
public class AppDTO {
    @Override
    public String toString() {
        return "AppDTO{" +
                "tenantId='" + tenantId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", rentType='" + rentType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tenantRemark='" + tenantRemark + '\'' +
                ", appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", appLogo='" + appLogo + '\'' +
                ", appMailUrl='" + appMailUrl + '\'' +
                ", appRemark='" + appRemark + '\'' +
                '}';
    }

    private String tenantId;
    private String tenantName;
    private String rentType;
    private Date startDate;
    private Date endDate;
    private String tenantRemark;
    private String appId;
    private String appName;
    private String appLogo;
    private String appMailUrl;
    private String appRemark;

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

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTenantRemark() {
        return tenantRemark;
    }

    public void setTenantRemark(String tenantRemark) {
        this.tenantRemark = tenantRemark;
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

    public String getAppLogo() {
        return appLogo;
    }

    public void setAppLogo(String appLogo) {
        this.appLogo = appLogo;
    }

    public String getAppMailUrl() {
        return appMailUrl;
    }

    public void setAppMailUrl(String appMailUrl) {
        this.appMailUrl = appMailUrl;
    }

    public String getAppRemark() {
        return appRemark;
    }

    public void setAppRemark(String appRemark) {
        this.appRemark = appRemark;
    }
}
