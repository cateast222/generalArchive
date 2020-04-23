package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.BaseAuditingEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;

/**
 * 单位
 * @author lwy
 * @Date 2019-05-31 11:48
 */
@Data
public class UnitDTO {

    private String id;

    /**
     * 所属单位分类，就用字典去做，添加一个字典叫 单位分类，unitClassValue就是字典name
     */
    @ApiModelProperty(value = "单位分类名")
    private String unitClassName;

    @ApiModelProperty(value = "单位分类字典值表的ID")
    private String dictValueId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String name;

    /**
     * 代码
     */
    @ApiModelProperty(value = "代码")
    private String code;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String simpleName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 上报名称
     */
    @ApiModelProperty(value = "上报名称")
    private String reportName;

    /**
     * 上报代码
     */
    @ApiModelProperty(value = "上报代码")
    private String reportCode;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
