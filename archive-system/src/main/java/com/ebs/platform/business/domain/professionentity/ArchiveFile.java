package com.ebs.platform.business.domain.professionentity;

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 档案文本
 * 
 */
@Data
@Entity(name = "archive_file")
public class ArchiveFile extends BaseAutoIncrementEntity{

   /** 文件名*/
   @Column(name = "file_name")
   private String fileName;
   /** 文件地址 */
   @Column(name = "file_url")
   private String fileUrl;
   /** 关联文本id */
   @Column(name = "text_id")
   private Integer textId;
   /** 关联档案id */
   @Column(name = "archive_id")
   private Integer archiveId;

}