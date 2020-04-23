package com.ebs.platform.business.dto.archive;

import lombok.Data;

/**
 * Created by liujie on 2019/11/13.
 *  档案新增和修改时动态创建表单需要的参数
 */
@Data
public class ArchiveFormDTO {

    /**
     * 档案类别
     */
    private String archiveType;
    /**
     * 档案级别
     */
    private Integer archiveLevel;
    /**
     * 归档状态(0:预归档 1:已归档)
     */
    private Integer archiveStatus;

    private Integer id;

}
