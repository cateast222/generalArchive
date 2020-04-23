package com.ebs.platform.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:11
 */
@ApiModel(value="租户账户角色关系")
public class TenantAccountRoleDTO {
    @Override
    public String toString() {
        return "TenantAccountRoleDTO{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
    @ApiModelProperty(value="主键",name="id",required=true)
    private String id;
    @ApiModelProperty(value="账户ID",name="accountId",required=true)
    private String accountId;
    @ApiModelProperty(value="租户ID",name="tenantId",required=true)
    private String tenantId;
    @ApiModelProperty(value="角色ID",name="roleId",required=true)
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
