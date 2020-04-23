package com.ebs.platform.business.dto.archive;

import lombok.Data;

/**
 * Created by liujie on 2019/11/17.
 * 档号格式
 */
@Data
public class DocnumberFormatDTO {

    /** 序号
     *
     */
    private Integer docnumberOrder;
    /** 档案字段
     *
     */
    private String archiveCode;
    /** 分隔符
     *
     */
    private String splitCode;
    /** 关联档案类别
     *
     */
    private String archiveType;
    /** 关联档案级别
     *
     */
    private Integer archiveLevel;

    private Integer id;

}
