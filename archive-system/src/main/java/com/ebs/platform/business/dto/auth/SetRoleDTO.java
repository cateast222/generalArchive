package com.ebs.platform.business.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 16:16
 */
@ApiModel(value="设置对象")
@Data
public class SetRoleDTO {

	@ApiModelProperty(value="角色ID",name="roleId",required=true)
    private String roleId;

    /**
     * codes 适用于 powerIds 和  ruleIds
     */

    @ApiModelProperty(value="设置的值列表，codes 适用于 powerIds 和  ruleIds",name="codes",required=true)
    private List<String> codes;


}
