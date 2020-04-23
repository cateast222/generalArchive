package com.ebs.platform.business.dto.organization;

import com.ebs.platform.core.query.MetadataField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrganizationDTO {

    @ApiModelProperty(value = "机构编号",name ="id")
    private Integer id ;

    @MetadataField(name = "机构名称")
    @ApiModelProperty(value = "机构名称",name ="orgName")
    private String orgName;

    @MetadataField(name = "机构编码")
    @ApiModelProperty(value = "机构编码",name ="orgCode")
    private String orgCode;

    @MetadataField(name = "英文名称")
    @ApiModelProperty(value = "英文名称",name ="englishName")
    private String englishName;

    @MetadataField(name = "简称")
    @ApiModelProperty(value = "简称",name ="shortName")
    private String shortName;

    @MetadataField(name = "电话")
    @ApiModelProperty(value = "电话",name ="orgPhone")
    private String orgPhone;

    @MetadataField(name = "传真")
    @ApiModelProperty(value = "传真",name ="orgFax")
    private String orgFax;

    @MetadataField(name = "排序号")
    @ApiModelProperty(value = "排序号",name ="orgOrder")
    private Integer orgOrder;

    @MetadataField(name = "描述")
    @ApiModelProperty(value = "描述",name ="orgDesc")
    private String orgDesc;

    @ApiModelProperty(value = "已删除？",name ="deleted")
    private boolean deleted ;

}
