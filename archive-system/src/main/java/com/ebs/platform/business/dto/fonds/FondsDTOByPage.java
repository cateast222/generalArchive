package com.ebs.platform.business.dto.fonds;

import com.ebs.platform.core.query.IMetadataObject;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FondsDTOByPage implements IMetadataObject {
    @ApiModelProperty(value = "主键ID",name ="id")
    private Integer id;
    @ApiModelProperty(value = "全宗号",name = "code")
    private String code;
    @ApiModelProperty(value = "称号",name = "name" )
    private String name;
    @ApiModelProperty(value = "描述",name = "描述")
    private String description;
}
