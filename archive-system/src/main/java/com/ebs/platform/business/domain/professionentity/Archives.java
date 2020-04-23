package com.ebs.platform.business.domain.professionentity;
/***********************************************************************
 * Module:  Archives.java
 * Author:  liuji
 * Purpose: Defines the Class Archives
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import com.ebs.platform.core.query.MetadataField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 档案主表
 *
 * @pdOid dae0d818-f771-4fe8-b3ab-819b10e94dce */
@Data
@Entity(name = "archives")
public class Archives extends BaseAutoIncrementEntity{

   /** 档案类别
    * archive_type
    * @pdOid dfecfe34-5af3-4cd6-b282-dd991a68176e */
   @MetadataField(name = "档案类别(字符串)")
   @Column(name = "archive_type", length = 100)
   private String archiveType;
   /** 父id
    *
    * @pdOid f5101bee-cc9e-45e4-be88-b6801ce9e76c */
   @Column(name = "parent_id")
   private Integer parentId;
   /** 文件url
    *
    * @pdOid 09bc2f1b-2166-463e-9e82-89b672cd6014 */
   @Column(name = "file_url", length = 1000)
   private String fileUrl;
   /** 档号
    *
    * @pdOid afd4628e-022f-40f5-8d7a-f66d7b3b9464 */
   @Column(name = "archive_code", length = 100)
   private String archiveCode;
   /** 全宗号
    *
    * @pdOid afd4628e-022f-40f5-8d7a-f66d7b3b9464 */
   @MetadataField(name = "全宗号（字符串）")
   @Column(name = "archive_fonds", length = 100)
   private String archiveFonds;
   /** 实体分类名称
    *
    * @pdOid afd4628e-022f-40f5-8d7a-f66d7b3b9464 */
   @MetadataField(name = "实体分类名称(字符串)")
   @Column(name = "entity_type", length = 100)
   private String entityType;


   /** 题名
    *
    * @pdOid 99874455-cba4-4ea4-801b-43500f0cb21a */
   @MetadataField(name = "题名（字符串）")
   @Column(name = "title", length = 100)
   private String title;
   /** 存放柜子
    *
    * @pdOid 57b7020e-d42b-400f-a6cf-73b04fee9dea */
   @Column(name = "archive_cabinet_id")
   private Integer archiveCabinetId;
    /** 存放行
    *
    * @pdOid 57b7020e-d42b-400f-a6cf-73b04fee9dea */
   @Column(name = "archive_row", length = 100)
   private String archiveRow;
    /** 存放列
    *
    * @pdOid 57b7020e-d42b-400f-a6cf-73b04fee9dea */
   @Column(name = "archive_column", length = 100)
   private String archiveColumn;
   /** 归档年度
    *
    * @pdOid 9d84c8f1-985d-4759-a744-04463b2dd8c2 */
   @MetadataField(name = "归档年度（字符串）")
   @Column(name = "archive_time", length = 100)
   private String archiveTime;
   /** 保管期限
    *
    * @pdOid f80c327f-d80b-43b8-8fb0-d9945b642d5f */
   @MetadataField(name = "保管期限（字符串）")
   @Column(name = "time_limit", length = 100)
   private String timeLimit;
   /** 档案级别（1:项目级 2:案卷级 3:文件级）
    *
    * @pdOid 142b2210-3122-45d7-86f2-0fad8face782 */
   @Column(name = "archive_level")
   private Integer archiveLevel;
   /** 归档状态(0:预归档 1:已归档 2:已销毁)
    *
    * @pdOid 9fe5826d-f431-4b76-ba8c-61352a8f8983 */
   @Column(name = "archive_status")
   private Integer archiveStatus;

   /** 关联全宗号
    *
    * @pdOid 9fe5826d-f431-4b76-ba8c-61352a8f8983 */
   @MetadataField(name = "全宗号（字符串）")
   @Column(name = "fonds_code")
   private Integer fondsCode;
   /** 关联实体分类号
    *
    * @pdOid 9fe5826d-f431-4b76-ba8c-61352a8f8983 */
   @MetadataField(name = "实体分类号（字符串）")
   @Column(name = "entity_type_code")
   private Integer entityTypeCode;
   /** 关联机构
    *
    * @pdOid 9fe5826d-f431-4b76-ba8c-61352a8f8983 */
   @Column(name = "organization_code")
   private Integer organizationCode;

     /** 页数
    *
    * @pdOid 9fe5826d-f431-4b76-ba8c-61352a8f8983 */
   @Column(name = "number_of_pages")
   private Integer numberOfPages;

   /** @pdOid e88a2289-3bc8-4872-b3d6-01c551d2cd78 */
   @Column(name = "c1")
   private String c1;
   /** @pdOid 2619591a-8e57-4793-8ea0-46b51f2ccb0f */
   @Column(name = "c2")
   private String c2;
   /** @pdOid 76e4b4a5-8c5c-4526-93da-74a6f092db41 */
   @Column(name = "c3")
   private String c3;
   /** @pdOid 93f87b4e-f5dd-40f3-8516-b89c7028b1be */
   @Column(name = "c4")
   private String c4;
   /** @pdOid 486e341b-2b1d-4358-bb62-c20b14dd3d1a */
   @Column(name = "c5")
   private String c5;
   /** @pdOid fe2bdbfd-fdf9-406c-b367-32125e684861 */
   @Column(name = "c6")
   private String c6;
   /** @pdOid f2fe9904-1386-417b-aef6-0f589126ae77 */
   @Column(name = "c7")
   private String c7;
   /** @pdOid 31e0d8a9-1247-4a29-9bd4-96b4095fa433 */
   @Column(name = "c8")
   private String c8;
   /** @pdOid e33e0221-5d4a-4fdf-9bae-438bf9d82b4a */
   @Column(name = "c9")
   private String c9;
   /** @pdOid 91db0ab6-82c5-4486-b46b-8a6aa898fd96 */
   @Column(name = "c10")
   private String c10;
   /** @pdOid c83836ad-e1f0-406c-a468-b464324a6302 */
   @Column(name = "c11")
   private String c11;
   /** @pdOid c82cb5f2-6223-485c-9ff8-d11caa18dffb */
   @Column(name = "c12")
   private String c12;
   /** @pdOid 0d322f1f-25f3-4952-a798-bd76f8d611d4 */
   @Column(name = "c13")
   private String c13;
   /** @pdOid c97a9098-b76f-4070-9d70-bdce7562ed33 */
   @Column(name = "c14")
   private String c14;
   /** @pdOid 8d961608-9e02-4a20-9f05-d114c3ced0a0 */
   @Column(name = "c15")
   private String c15;
   /** @pdOid 9422ceac-2b70-4e7b-868f-b9399c6031a2 */
   @Column(name = "c16")
   private String c16;
   /** @pdOid 6c7899fa-6ba4-4503-9dcc-431503afbc40 */
   @Column(name = "c17")
   private String c17;
   /** @pdOid aa9fac16-9994-4a73-a669-fedb36241810 */
   @Column(name = "c18")
   private String c18;
   /** @pdOid c9449876-679e-44b8-82e1-373dc75f602e */
   @Column(name = "c19")
   private String c19;
   /** @pdOid 9b99fcce-f5aa-4d72-9c12-371cf52aec4d */
   @Column(name = "c20")
   private String c20;
   /** @pdOid 53ed6d69-d739-4808-90ac-dfee11de64d5 */
   @Column(name = "c21")
   private String c21;
   /** @pdOid cdb8c8e2-40c1-42bb-b257-129ecc2460d2 */
   @Column(name = "c22")
   private String c22;
   /** @pdOid 2cb058ff-11f5-486a-850f-a8d2cd62351a */
   @Column(name = "c23")
   private String c23;
   /** @pdOid f29760ad-b3a5-4a50-aa11-8c60f62fffe1 */
   @Column(name = "c24")
   private String c24;
   /** @pdOid fae71e2e-70d4-46d7-b1a6-627682a2c372 */
   @Column(name = "c25")
   private String c25;
   /** @pdOid a08c722e-6ca9-471d-83aa-7d134bb25e9b */
   @Column(name = "c26")
   private String c26;
   /** @pdOid 88fa9767-d23d-436a-88d4-b4246c2b0eb2 */
   @Column(name = "c27")
   private String c27;
   /** @pdOid b70727d7-c11f-40b6-84d3-15d2afcb691d */
   @Column(name = "c28")
   private String c28;
   /** @pdOid 30be20a1-dc92-4f79-bf0e-0d542c4ab80d */
   @Column(name = "c29")
   private String c29;
   /** @pdOid cef33a10-fe0b-44b0-9273-ad54e90048ae */
   @Column(name = "c30")
   private String c30;
   /** @pdOid 1a1d548e-af30-47f1-befe-23775178a3f0 */
   @Column(name = "c31", length = 1000)
   private String c31;
   /** @pdOid 2a118f29-3159-4137-a199-8886a3d31f4d */
   @Column(name = "c32", length = 1000)
   private String c32;
   /** @pdOid 2ee89097-0b19-4c82-a416-eeced71b6112 */
   @Column(name = "c33", length = 1000)
   private String c33;
   /** @pdOid 35187486-225d-4264-8b2c-72bb19f06f61 */
   @Column(name = "c34", length = 1000)
   private String c34;
   /** @pdOid 7108611f-4694-42d7-bc71-17e61c651ba3 */
   @Column(name = "c35", length = 1000)
   private String c35;

}
