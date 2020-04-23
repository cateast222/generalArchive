package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  Fonds.java
 * Author:  liuji
 * Purpose: Defines the Class Fonds
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import com.ebs.platform.core.query.IMetadataObject;
import com.ebs.platform.core.query.MetadataField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 全宗管理，全宗是指一个独立机关、组织或个人在社会活动中形成的档案有机整体，它的基本涵义有三点，即全宗是一个有机整体，全宗是在一定的历史活动中形成的，全宗是以一定的社会单位为基础而构成的。
 *
 * @pdOid 6d990c34-3d29-4d39-938c-2a9c09f23c23 */
@Data
@Entity(name = "fonds")
public class Fonds extends BaseAutoIncrementEntity  implements IMetadataObject {

   /** 全宗号
    *
    * @pdOid ee5d1776-a0f1-4c4a-ba99-50d84cb01b53 */
   @MetadataField(name="全宗号")
   @Column(name = "code", length = 100)
   private String code;
   /** 名称
    *
    * @pdOid cf49f3ea-0b39-4f25-830a-8efc3b17fd14 */
   @MetadataField(name ="名称")
   @Column(name = "name", length = 100)
   private String name;
   /** 描述
    *
    * @pdOid 3690d3eb-8c2b-434c-a41f-29284b0f6848 */
   @MetadataField(name = "描述")
   @Column(name = "description")
   private String description;

}
