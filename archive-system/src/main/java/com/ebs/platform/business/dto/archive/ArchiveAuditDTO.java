package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.DictionaryKey;
import com.ebs.platform.core.query.MetadataField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by liujie on 2019/11/27.
 * @author liujie
 */
@Data
public class ArchiveAuditDTO{

    /**
     * 审核id
     *
     * */
    @ApiModelProperty(value = "审核编号")
    private Integer id;
    /**
     * 鉴定 id
     * */
    @ApiModelProperty(value = "鉴定编号")
    private Integer identifyId ;
    /** 修改保管期限
     *
     */
    @DictionaryKey(key = "RETENTIONPERIOD")
    @MetadataField(name = "保管期限")
    @ApiModelProperty(value = "修改保管期限")
    private String keepTime;
    /** 鉴定人
     *
     */
    @MetadataField(name = "鉴定人")
    @ApiModelProperty(value = "鉴定人")
    private String identifyPerson;
    /** 鉴定日期
     *
     */
    @MetadataField(name = "鉴定日期")
    @ApiModelProperty(value = "鉴定日期")
    private Date identifyDate;
    /** 鉴定原因
     *
     */
    @MetadataField(name = "鉴定原因")
    @ApiModelProperty(value = "鉴定原因")
    private String identifyReason;
    /** 鉴定说明
     *
     */
    @MetadataField(name = "鉴定说明")
    @ApiModelProperty(value = "鉴定说明")
    private String identifyExplain;
    /** 是否销毁(1:是 0:否)
     *
     */
    @ApiModelProperty(value = "是否销毁")
    private Integer isDestroy;
    /** 关联档案id
     *
     */
    @ApiModelProperty(value = "关联档案id")
    private String archiveId;

    @MetadataField(name = "审核意见")
    @ApiModelProperty(value = "审核意见")
    private String auditIdea;

    @MetadataField(name = "审核人")
    @ApiModelProperty(value = "审核人")
    private String auditPerson;

    @MetadataField(name = "审核日期")
    @ApiModelProperty(value = "审核日期")
    private Date auditDate;

    @MetadataField(name = "审核状态")
    @ApiModelProperty(value = "审核状态")
    @DictionaryKey(key = "AUDITSTATUS")
    private Integer auditStatus;

    @MetadataField(name = "备注")
    @ApiModelProperty(value = "备注")
    private String auditRemark;


}
