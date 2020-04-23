package com.ebs.platform.business.dto.archive;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by liujie on 2019/11/25.
 */
@Data
public class ArchiveFileDTO {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "文件地址")
    private String fileUrl;
    @ApiModelProperty(value = "档案id")
    private Integer archiveId;

}
