package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  DocnumberFormat.java
 * Author:  liuji
 * Purpose: Defines the Class DocnumberFormat
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import com.ebs.platform.core.query.MetadataField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 档号格式
 *
 * @pdOid 2ff1082f-f3d2-4f6e-9e8e-7fe0c5c7887f */
@Data
@Entity(name = "docnumber_format")
public class DocnumberFormat extends BaseAutoIncrementEntity{

   /** 序号
    *
    * @pdOid 6105da66-5289-4708-9c75-2c3336709ba1 */
   @Column(name = "docnumber_order")
   private Integer docnumberOrder;
   /** 档案字段
    *
    * @pdOid a4fd0a6c-87c9-4093-b93f-65e77e8f3cac */
   @Column(name = "archive_code", length = 100)
   private String archiveCode;
   /** 分隔符
    *
    * @pdOid 39f04459-a059-4afe-be28-ce21e91ee246 */
   @Column(name = "split_code", length = 100)
   private String splitCode;

   /**
    * 内容值（0 编码 1 名称 2 无）
    *
    * */
   @Column(name = "content")
   private Integer content;

   /**
    *长度
    *
    * */
   @Column(name = "length")
   private Integer length;

   /** 关联档案类别
    *
    * @pdOid d51eab95-75a5-4303-9772-78e9be342817 */
   @Column(name = "archive_type", length = 100)
   private Integer archiveType;
   /** 关联档案级别
    *
    * @pdOid f729a3ea-e3b0-4ec1-9f50-4f6d7cd40978 */
   @Column(name = "archive_level")
   private Integer archiveLevel;

}
