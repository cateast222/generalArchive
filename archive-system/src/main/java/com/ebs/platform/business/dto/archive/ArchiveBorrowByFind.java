package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.DictionaryKey;
import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: LiuWY
 * @Date: 2019/11/28 11:34
 */
@Data
public class ArchiveBorrowByFind implements IMetadataObject{

    @ApiModelProperty(value = "被借阅者")
    @MetadataField(name = "被借人")
    private String beBorrower;

    @ApiModelProperty(value = "借阅者")
    @MetadataField(name = "借阅人")
    private String borrower;

    @ApiModelProperty(value = "联系电话")
    @MetadataField(name = "联系电话")
    private String phone;

    @ApiModelProperty(value = "单位")
    @MetadataField(name = "单位",isColumn = false,isFilterHidden = true)
    private String unitId;

    @ApiModelProperty(value = "借阅时间当前时间")
    @MetadataField(name = "借阅时间")
    private Date borrowTime;

    @ApiModelProperty(value = "归还时间实物借阅")
    @MetadataField(name = "归还时间")
    private Date returnTime;

    @ApiModelProperty(value = "借阅类型0在线1实物")
    @MetadataField(name = "借阅类型")
    //@DictionaryKey(key = "不变", noChangeData = "[{\"name\":\"在线\",\"id\":\"0\",\"value\":\"0\"},{\"name\":\"实物\",\"id\":\"1\",\"value\":\"1\"}]")
    private String borrowType;

    @ApiModelProperty(value = "实物借阅状态0未还1已还")
    //@DictionaryKey(key = "不变", noChangeData = "[{\"name\":\"未还\",\"id\":\"0\",\"value\":\"0\"},{\"name\":\"已还\",\"id\":\"1\",\"value\":\"1\"}]")
    @MetadataField(name = "实物借阅状态")
    private String returnStatus;

}
