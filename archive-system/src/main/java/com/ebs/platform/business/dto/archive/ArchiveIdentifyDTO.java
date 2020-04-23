package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.BaseAutoIncrementEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by liujie on 2019/11/27.
 * @author liujie
 */
@Data
public class ArchiveIdentifyDTO extends BaseAutoIncrementEntity{

    /** 修改保管期限
     *
     */
    @ApiModelProperty(value = "修改保管期限")
    private String keepTime;
    /** 鉴定人
     *
     */
    @ApiModelProperty(value = "鉴定人")
    private String identifyPerson;
    /** 鉴定日期
     *
     */
    @ApiModelProperty(value = "鉴定日期")
    private Date identifyDate;
    /** 鉴定原因
     *
     */
    @ApiModelProperty(value = "鉴定原因")
    private String identifyReason;
    /** 鉴定说明
     *
     */
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

}
