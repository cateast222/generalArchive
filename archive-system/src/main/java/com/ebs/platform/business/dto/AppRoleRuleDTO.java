package com.ebs.platform.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:07
 */
@ApiModel(value="应用角色关联的API规则")
public class AppRoleRuleDTO {
    @Override
    public String toString() {
        return "AppRoleRuleDTO{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                '}';
    }
    @ApiModelProperty(value="主键",name="id")
    private String id;
    @ApiModelProperty(value="应用角色ID",name="roleId")
    private String roleId;
    @ApiModelProperty(value="规则ID",name="ruleId")
    private String ruleId;

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

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}
