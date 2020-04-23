package com.ebs.platform.business.dto.organization;

import com.ebs.platform.core.query.MetadataField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrganizationRequestDTO {

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

}
