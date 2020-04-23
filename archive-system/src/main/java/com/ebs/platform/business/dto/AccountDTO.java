package com.ebs.platform.business.dto;

import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:00
 */
@Data
@ApiModel(value="账户对象")
public class AccountDTO extends BaseEntity {

    @ApiModelProperty(value="用户名",name="username",required=true)
    private String userName;

    @JsonIgnore
    @ApiModelProperty(value="密码",name="password",required=true)
    private String password;

    @ApiModelProperty(hidden = true)
    private String currentTenantId;

    private UserTypeEnum userType;

}
