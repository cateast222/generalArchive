package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.IMetadataObject;
import lombok.Data;

/**
 * Created by liujie on 2019/11/14.
 * 查询列表需要的请求参数
 */
@Data
public class ArchivesQueryParamDTO implements IMetadataObject {

    /** 档案类别
     * archive_type
     * @pdOid dfecfe34-5af3-4cd6-b282-dd991a68176e */
    private String archiveType;

    /** 档案级别（1:项目级 2:案卷级 3:文件级）
     *
     * @pdOid 142b2210-3122-45d7-86f2-0fad8face782 */
    private Integer archiveLevel;
    /** 归档状态(0:预归档 1:已归档)
     *
     * */
    private Integer archiveStatus;

    /**
     * 查询列表的方法名
     */
    private String functionName;

}
