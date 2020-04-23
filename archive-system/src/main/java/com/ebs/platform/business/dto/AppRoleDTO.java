package com.ebs.platform.business.dto;

import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:05
 */
@Data
@ApiModel(value="应用角色对象")
public class AppRoleDTO extends BaseEntity {
    @Override
    public String toString() {
        return "AppRoleDTO{" +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }

    @ApiModelProperty(value="角色名称",name="name",required=true)
    private String name;

    @ApiModelProperty(value="角色编码",name="code",required=true)
    private String code;

    @ApiModelProperty(value="所属租户(当指定后，只有该租户可见)",name="tenantId")
    private String tenantId;

}
