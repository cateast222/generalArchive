package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  ArchiveText.java
 * Author:  liuji
 * Purpose: Defines the Class ArchiveText
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 档案文本
 * 
 * @pdOid 762db957-f2da-48ce-a20a-76eb4641290a */
@Data
@Entity(name = "archive_text")
public class ArchiveText extends BaseAutoIncrementEntity{

   /** 关联档案id
    * 
    * @pdOid c7305bd6-4038-4852-9ed6-0a1e24036340 */
   @Column(name = "archive_id", length = 100)
   private Integer archiveId;
   /** 档案内容
    * 
    * @pdOid 1fee9b4d-ec21-4388-a3ee-21fcdc12be0b */
   @Column(name = "archive_text")
   private String archiveText;

}