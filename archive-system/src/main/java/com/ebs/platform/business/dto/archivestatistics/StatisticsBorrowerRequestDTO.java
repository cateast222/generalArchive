package com.ebs.platform.business.dto.archivestatistics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
@Data
public class StatisticsBorrowerRequestDTO {
    @ApiModelProperty(value = "借阅时间开始",name = "borrowerStart")
    private Date borrowerStart;
    @ApiModelProperty(value = "借阅时间结束",name = "borrowerEnd")
    private Date borrowerEnd;
    @ApiModelProperty(value = "利用方式",name = "useParttern")
    private String useParttern;
    @ApiModelProperty(value = "借阅目的",name = "borrowPurpose")
    private String borrowPurpose;
    @ApiModelProperty(value = "借阅类型(0:实物借阅，1:在线借阅)",name = "borrowerType")
    private Integer borrowerType;

}
