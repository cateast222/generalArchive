package com.ebs.platform.business.dto.archive;

import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import lombok.Data;

import java.util.List;

/**
 * Created by liujie on 2019/11/4.
 */
@Data
public class ArchivesDTO implements IMetadataObject {

    private Integer id;

    /** 档案类别
     * archive_type
     * @pdOid dfecfe34-5af3-4cd6-b282-dd991a68176e */
    private String archiveType;
    /** 父id
     *
     * @pdOid f5101bee-cc9e-45e4-be88-b6801ce9e76c */
    private Integer parentId;
    /** 文件url
     *
     * @pdOid 09bc2f1b-2166-463e-9e82-89b672cd6014 */
    private String fileUrl;
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
    /** 实体分类名称
     *
     * @pdOid afd4628e-022f-40f5-8d7a-f66d7b3b9464 */
    private String entityType;

    /** 题名
     *
     * @pdOid 99874455-cba4-4ea4-801b-43500f0cb21a */
    @MetadataField(name = "题名")
    private String title;
    /**
     * 存放柜子
     */
    private List<Integer> archiveCabinetIdList;
    /** 存放行
     *
     * @pdOid 57b7020e-d42b-400f-a6cf-73b04fee9dea */
    private String archiveRow;
    /** 存放列
     *
     * @pdOid 57b7020e-d42b-400f-a6cf-73b04fee9dea */
    private String archiveColumn;
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
    private Integer fondsCode;
    /** 关联实体分类号
     *
     * @pdOid 9fe5826d-f431-4b76-ba8c-61352a8f8983 */
    private Integer entityTypeCode;
    /** 关联机构
     *
     * @pdOid 9fe5826d-f431-4b76-ba8c-61352a8f8983 */
    private Integer organizationCode;
    private Integer numberOfPages;
    private Integer archiveStatus;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    private String c7;
    private String c8;
    private String c9;
    private String c10;
    private String c11;
    private String c12;
    private String c13;
    private String c14;
    private String c15;
    private String c16;
    private String c17;
    private String c18;
    private String c19;
    private String c20;
    private String c21;
    private String c22;
    private String c23;
    private String c24;
    private String c25;
    private String c26;
    private String c27;
    private String c28;
    private String c29;
    private String c30;
    private String c31;
    private String c32;
    private String c33;
    private String c34;
    private String c35;

}
