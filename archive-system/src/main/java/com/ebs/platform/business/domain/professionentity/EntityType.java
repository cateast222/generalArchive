package com.ebs.platform.business.domain.professionentity;
/***********************************************************************
 * Module:  EntityType.java
 * Author:  liuji
 * Purpose: Defines the Class EntityType
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 实体分类
 * 
 * @pdOid e9bc6603-9104-4eb0-86f0-7b8acda757ac */
@Data
@Entity(name = "entity_type")
public class EntityType extends BaseAutoIncrementEntity{

   /** 实体分类名称
    * 
    * @pdOid f0c11e5f-d31e-41ec-9e21-11b6594066bf */
   @Column(name = "entity_name", length = 100)
   private String entityName;
   /** 实体分类编号
    * 
    * @pdOid fe903f79-ecad-43f6-8b6d-703d6dc842e5 */
   @Column(name = "entity_code", length = 100)
   private String entityCode;
   /** 父实体类编号
    * 
    * @pdOid f39fea1c-1c56-431b-ac27-cffbd0d3fc4c */
   @Column(name = "parent_code", length = 100)
   private String parentCode;
   /** 排序号
    * 
    * @pdOid e5f5e21b-9958-44c6-ba7f-7c2f998773ef */
   @Column(name = "entity_order")
   private Integer entityOrder;
   /** 描述
    * 
    * @pdOid 3b1e0dbf-75ca-404d-b6e7-688f6adb650c */
   @Column(name = "entity_desc")
   private String entityDesc;
   /** 关联全宗号
    * 
    * @pdOid d50c1c33-c09d-4707-abed-33ebf47cbe8f */
   @Column(name = "entity_fonds", length = 100)
   private String entityFonds;


}