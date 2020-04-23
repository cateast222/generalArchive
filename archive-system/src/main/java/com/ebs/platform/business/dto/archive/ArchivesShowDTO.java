package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import lombok.Data;

import java.util.List;

/**
 * Created by liujie on 2019/11/27.
 */
@Data
public class ArchivesShowDTO implements IMetadataObject {

    private Integer id;

    /** 父id
     *
     * @pdOid f5101bee-cc9e-45e4-be88-b6801ce9e76c */
    private Integer parentId;
    /** 档号
     *
     * @pdOid afd4628e-022f-40f5-8d7a-f66d7b3b9464 */
    @MetadataField(name = "档号")
    private String archiveCode;

    /** 全宗号
     *
     * @pdOid afd4628e-022f-40f5-8d7a-f66d7b3b9464 */
    @MetadataField(name = "全宗号")
    private String archiveFonds;

    /** 题名
     *
     * @pdOid 99874455-cba4-4ea4-801b-43500f0cb21a */
    @MetadataField(name = "题名")
    private String title;
    /** 归档年度
     *
     * @pdOid 9d84c8f1-985d-4759-a744-04463b2dd8c2 */
    @MetadataField(name = "归档年度")
    private String archiveTime;
    /** 保管期限
     *
     * @pdOid f80c327f-d80b-43b8-8fb0-d9945b642d5f */
    private String timeLimit;
    /** 档案级别（1:项目级 2:案卷级 3:文件级）
     *
     * @pdOid 142b2210-3122-45d7-86f2-0fad8face782 */
    private Integer archiveLevel;
    /** 归档状态(0:预归档 1:已归档)
     *
     * */
    private Integer archiveStatus;

    private List<Object> children;

}
