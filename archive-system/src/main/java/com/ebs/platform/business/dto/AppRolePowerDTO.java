package com.ebs.platform.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:06
 */
@ApiModel(value="应用角色关联的权限")
public class AppRolePowerDTO {
    @Override
    public String toString() {
        return "AppRolePowerDTO{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", powerCode='" + powerCode + '\'' +
                '}';
    }
    @ApiModelProperty(value="主键",name="id")
    private String id;
    @ApiModelProperty(value="角色ID",name="roleId",required=true)
    private String roleId;
    @ApiModelProperty(value="前台权限编码",name="powerCode",required=true)
    private String powerCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPowerCode() {
        return powerCode;
    }

    public void setPowerCode(String powerCode) {
        this.powerCode = powerCode;
    }
}
