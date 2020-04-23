package com.ebs.platform.business.dto.archivestatistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ArchiveStatisticsRequestDTO {

    @ApiModelProperty(value = "全宗号",name = "code")
    private String code;
    @ApiModelProperty(value = "归档年度",name = "year")
    private String year;
    @ApiModelProperty(value = "归档开始时间",name = "archiveStart")
    private Date archiveStart;
    @ApiModelProperty(value = "归档结束时间",name = "archiveEnd")
    private Date archiveEnd;
}
