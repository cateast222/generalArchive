package com.ebs.platform.business.dto.archivetext;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ArchiveTextSaveDTO {
    @ApiModelProperty(value = "原文id",name = "id")
    private Integer id;
    @ApiModelProperty(value = "原文内容",name = "archiveText")
    private String archiveText;
    @ApiModelProperty(value = "文件id",name = "archiveId")
    private Integer archiveId;
}
