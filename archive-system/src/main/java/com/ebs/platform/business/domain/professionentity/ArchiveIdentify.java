package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  ArchiveIdentify.java
 * Author:  liuji
 * Purpose: Defines the Class ArchiveIdentify
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.*;

/** 鉴定表
 * 
 * @pdOid dc8bd517-9db5-43eb-8fa1-370cdf09a483 */
@Data
@Entity(name = "archive_identify")
public class ArchiveIdentify extends BaseAutoIncrementEntity{

   /** 修改保管期限
    * 
    * @pdOid 611b3b95-a05f-4adf-9093-cbd7c60371d6 */
   @Column(name = "keep_time", length = 100)
   private String keepTime;
   /** 鉴定人
    * 
    * @pdOid f9deaacd-f6bb-47f8-947a-da68a196e6d1 */
   @Column(name = "identify_person", length = 100)
   private String identifyPerson;
   /** 鉴定日期
    * 
    * @pdOid 24d41887-fc72-479e-82e3-7baa250d1686 */
   @Column(name = "identify_date")
   private Date identifyDate;
   /** 鉴定原因
    * 
    * @pdOid a62d4c60-bed2-482f-ac22-6e6393b6b6f5 */
   @Column(name = "identify_reason")
   private String identifyReason;
   /** 鉴定说明
    * 
    * @pdOid 466a1422-6fe3-4a6a-a2f1-9e65a7d53df6 */
   @Column(name = "identify_explain", length = 100)
   private String identifyExplain;
   /** 是否销毁(1:是 0:否)
    * 
    * @pdOid 177fb838-6010-44b4-bd76-f77c0bafbd00 */
   @Column(name = "is_destroy", length = 100)
   private Integer isDestroy;
   /** 关联档案id
    * 
    * @pdOid 2f22a69a-ff81-4655-9181-a16fc6a9a7b9 */
   @Column(name = "archive_id", length = 2000)
   private String archiveId;

}