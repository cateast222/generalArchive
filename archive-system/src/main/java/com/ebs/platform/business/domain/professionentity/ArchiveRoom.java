package com.ebs.platform.business.domain.professionentity;
/***********************************************************************
 * Module:  ArchiveRoom.java
 * Author:  liuji
 * Purpose: Defines the Class ArchiveRoom
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 档案室
 * 
 * @pdOid 21a6e2b3-9315-4618-b011-f7e1c059e9b0 */
@Data
@Entity(name = "tb_archive_room")
public class ArchiveRoom extends BaseAutoIncrementEntity{

   /** 名称，例如 几室、几区、几柜
    * 
    * @pdOid fd2e8655-7161-479c-96a1-1d5399773bdb */
   @Column(name = "name")
   private String name;

   /** 级别
    * 
    * @pdOid c417c386-7769-4aca-94f5-2238906d4027 */
   @Column(name = "the_rank")
   private Integer theRank;
   /** 父类id
    * 
    * @pdOid 6ef002eb-a08b-46e0-ae04-c3cb53a66589 */
   @Column(name = "parent_id", length = 100)
   private Integer parentId;
   /** 柜子的行
    * 
    * @pdOid 43a37f91-ee3f-41b7-a904-ad7718197674 */
   @Column(name = "the_row", length = 100)
   private String theRow;
   /** 柜子的列
    * 
    * @pdOid f2a3890f-7e01-477e-8746-e1ffe1b636c6 */
   @Column(name = "the_column", length = 100)
   private String theColumn;
   /** 备注
    * 
    * @pdOid 66be63a0-fc7a-4160-9d04-41e65dccdd0c */
   @Column(name = "remark")
   private String remark;
   /** 容量
    * 
    * @pdOid 47613f4f-ddd2-44e0-99c7-6e6e5c23f774 */
   @Column(name = "amount")
   private Integer amount;


}