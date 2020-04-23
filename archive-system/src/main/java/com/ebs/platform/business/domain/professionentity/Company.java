package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  Company.java
 * Author:  liuji
 * Purpose: Defines the Class Company
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 单位信息
 * 
 * @pdOid d4f7ef04-9f60-4e92-b144-fa52f0d5edcb */
@Data
@Entity(name = "company")
public class Company extends BaseAutoIncrementEntity{

   /** 单位名称
    * 
    * @pdOid 1721a053-e744-49f3-bed7-86e131dfa084 */
   @Column(name = "company_name")
   private String companyName;
   /** 单位类别编码
    * 
    * @pdOid afeb4cfa-5378-4eaa-aef2-7fa67e5dcd3d */
   @Column(name = "type_code", length = 100)
   private String typeCode;
   /** 单位编码
    * 
    * @pdOid cbe9ce16-5927-4a3c-b344-ab6be08b7712 */
   @Column(name = "company_code", length = 100)
   private String companyCode;
   /** 联系人
    * 
    * @pdOid 590de3ca-7de4-40f7-a512-1cc6187cd0d6 */
   @Column(name = "link_man", length = 100)
   private String linkMan;
   /** 联系电话
    * 
    * @pdOid 9fa3beb7-4b4f-473a-bcaf-73f0b0557383 */
   @Column(name = "link_phone")
   private String linkPhone;
   /** 单位地址
    * 
    * @pdOid 3a1a4374-32fe-424d-abf0-290199f3b3c1 */
   @Column(name = "address", length = 100)
   private String address;

}