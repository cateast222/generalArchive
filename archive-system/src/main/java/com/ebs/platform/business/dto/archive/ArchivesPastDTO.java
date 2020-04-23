package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by liujie on 2019/12/3.
 */
@Data
public class ArchivesPastDTO implements IMetadataObject, Serializable {

    /**
     * 档号
     */
    @MetadataField(name = "档号")
    private String archiveCode;

    /**
     * 提名
     */
    @MetadataField(name = "提名")
    private String title;

    /**
     * 提名
     */
    @MetadataField(name = "归档年度")
    private String archiveTime;

    /**
     * 保管期限
     */
    @MetadataField(name = "保管期限")
    private String timeLimit;



}
