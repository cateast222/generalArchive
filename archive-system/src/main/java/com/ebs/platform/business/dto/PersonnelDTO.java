package com.ebs.platform.business.dto;

import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.enums.SexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:09
 */
@Data
@ApiModel(value="人员对象")
public class PersonnelDTO extends BaseEntity {

    @ApiModelProperty(value="所属企业ID",name="entId",required=true)
    private String entId;

    @ApiModelProperty(value="所属部门ID",name="deptId")
    private String deptId;

    @ApiModelProperty(value="所属账户ID",name="accountId")
    private String accountId;

    @ApiModelProperty(value="人员名称",name="name")
    private String name;

    @ApiModelProperty(value="人员性别",name="sex")
    private SexEnum sex;

    @ApiModelProperty(value="手机号码",name="tel")
    private String tel;

    @ApiModelProperty(value="电子邮箱",name="email")
    private String email;

    @ApiModelProperty(value="人员生日",name="birthDate")
    private Date birthDate;

}
