package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  Organization.java
 * Author:  liuji
 * Purpose: Defines the Class Organization
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 机构管理
 * 
 * @pdOid 4a40c1fa-d7c3-4433-b419-c3218c5652f5 */
@Data
@Entity(name = "organization")
public class Organization extends BaseAutoIncrementEntity{

   /** 机构名称
    * 
    * @pdOid e1db02c9-29e8-4b21-821b-719da72285b0 */
   @Column(name = "org_name", length = 100)
   private String orgName;
   /** 机构编码
    * 
    * @pdOid b96e2e1b-de04-4288-86e5-7e2300e65730 */
   @Column(name = "org_code", length = 100)
   private String orgCode;
   /** 英文名称
    * 
    * @pdOid 696cffe6-97a0-4392-a7f5-38f222d2bcfa */
   @Column(name = "english_name")
   private String englishName;
   /** 简称
    * 
    * @pdOid c227bc22-ed33-4259-a6b2-5196db130588 */
   @Column(name = "short_name", length = 100)
   private String shortName;
   /** 电话
    * 
    * @pdOid 2f8ccc1b-8bc8-465b-acd0-dee9ef3d193c */
   @Column(name = "org_phone", length = 100)
   private String orgPhone;
   /** 传真
    * 
    * @pdOid 207d3b76-3eee-448c-bb61-85631421611f */
   @Column(name = "org_fax", length = 100)
   private String orgFax;
   /** 排序号
    * 
    * @pdOid 626c4b1d-f085-47f5-a6b9-273f5f1f7589 */
   @Column(name = "org_order")
   private Integer orgOrder;
   /** 描述
    * 
    * @pdOid 1e9f1609-72e9-4abd-aed0-80d45cb9ba1a */
   @Column(name = "org_desc")
   private String orgDesc;


}