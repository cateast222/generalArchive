package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  ArchiveBorrowItem.java
 * Author:  liuji
 * Purpose: Defines the Class ArchiveBorrowItem
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 借阅申请明细表
 * 
 * @pdOid 047b8266-6112-43fd-a927-eaea8acdf99d */
@Data
@Entity(name = "archive_borrow_item")
public class ArchiveBorrowItem extends BaseAutoIncrementEntity{

   /** 申请表ID**/
   @Column(name = "borrow_register_id", length = 100)
   private Integer borrowRegisterId;

   /** 借阅材料id**/
   @Column(name = "archive_item_id", length = 100)
   private Integer archiveItemId;

}