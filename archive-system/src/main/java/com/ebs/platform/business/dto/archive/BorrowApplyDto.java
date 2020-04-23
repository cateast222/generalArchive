package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.MetadataField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BorrowApplyDto {

    private String id;

    /** 借阅时间 **/
    @ApiModelProperty(value = "借阅时间")
    @MetadataField(name = "借阅时间")
    private Date borrowTime;

    /** 归还时间**/
    @ApiModelProperty(value = "归还时间")
    @MetadataField(name = "归还时间")
    private Date returnTime;

    /** 实际归还时间**/
    @ApiModelProperty(value = "实际归还时间")
    @MetadataField(name = "实际归还时间")
    private Date actualTime;

    /** 实物归还状态(0:未归还 1:已归还)**/
    @ApiModelProperty(value = "是否归还")
    @MetadataField(name = "是否归还")
    private Integer returnStatus;

    /** 借阅类型(0:实物借阅，1:在线借阅)**/
    @ApiModelProperty(value = "借阅类型")
    @MetadataField(name = "借阅类型")
    private Integer borrowType;

    /** 借阅目的（字典取值）**/
    @ApiModelProperty(value = "借阅目的")
    @MetadataField(name = "借阅目的")
    private String borrowPurpose;

    /** 借阅人范围(内部/外部)**/
    @ApiModelProperty(value = "借阅人范围")
    @MetadataField(name = "借阅人范围")
    private String borrowRange;

    /** 借阅人**/
    @ApiModelProperty(value = "借阅人")
    @MetadataField(name = "借阅人")
    private String borrower;

    /** 借阅账号借阅时生成的账号**/
    @ApiModelProperty(value = "借阅账号")
    @MetadataField(name = "借阅账号")
    private String userName;

    /** 利用方式(字典)**/
    @ApiModelProperty(value = "利用方式")
    @MetadataField(name = "利用方式")
    private String usePattern;

    /** 借阅说明**/
    @ApiModelProperty(value = "借阅说明")
    @MetadataField(name = "借阅说明")
    private String borrowExplain;

    /** 可选可输**/
    @ApiModelProperty(value = "批准人可选可输")
    @MetadataField(name = "批准人名称可选可输")
    private String approvalName;

    /** 审批单URL**/
    @ApiModelProperty(value = "审批单URL")
    @MetadataField(name = "审批单URL")
    private String approvalFormUrl;

    /** 部门**/
    @ApiModelProperty(value = "部门")
    @MetadataField(name = "部门")
    private String unitId;

    List<ArchiveBorrowItemDto> archiveBorrowItemDtoList;
}
