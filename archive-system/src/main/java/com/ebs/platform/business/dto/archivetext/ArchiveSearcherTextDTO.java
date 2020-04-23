package com.ebs.platform.business.dto.archivetext;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ArchiveSearcherTextDTO {

    @ApiModelProperty(value = "搜索类型",name = "searchType")
    private Integer searchType;

}
