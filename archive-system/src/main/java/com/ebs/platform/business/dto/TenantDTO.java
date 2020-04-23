package com.ebs.platform.business.dto;

import com.ebs.platform.core.enums.TenantTypeEnum;
import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:11
 */
@Data
@ApiModel(value="租户对象")
public class TenantDTO extends BaseEntity {

    @ApiModelProperty(value="所属应用ID",name="appId",required=true)
    private String appId;
    @ApiModelProperty(value="租赁起始日期",name="startDate",required=true)
    private Date startDate;
    @ApiModelProperty(value="租赁截至日期",name="endDate")
    private Date endDate;
    @ApiModelProperty(value="宿主ID(企业ID，账户ID)",name="rentId",required=true)
    private String rentId;
    @ApiModelProperty(value="租户类型",name="tenantType",required=true)
    private TenantTypeEnum tenantType;

}
