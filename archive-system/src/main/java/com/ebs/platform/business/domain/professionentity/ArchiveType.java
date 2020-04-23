package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  ArchiveType.java
 * Author:  liuji
 * Purpose: Defines the Class ArchiveType
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import com.ebs.platform.core.conf.AuditingTenantEntityListener;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/** 档案分类
 * 
 * @pdOid 4f9919ba-034c-4b32-8183-1eef9a53a617 */
@Data
@Entity(name = "archive_type")
public class ArchiveType extends BaseAutoIncrementEntity{

   /** 门类名称
    * 
    * @pdOid bb8bc5a7-d409-4198-b384-dd7b2e0bf53e */
   @Column(name = "type_name", length = 100)
   private String typeName;
   /** 门类编码
    * 
    * @pdOid 75603ca4-9083-4bc7-8303-a9a264929891 */
   @Column(name = "parent_name", length = 100)
   private String parentName;
   /** 父门类编码
    * 
    * @pdOid a78a9102-3422-44d9-a340-f8966ea40379 */
   @Column(name = "parent_code")
   private Integer parentCode;
   /** 文档目录层数
    * 
    * @pdOid a21adeed-9921-44e1-ad9c-eb1fcd068fa9 */
   @Column(name = "type_layer")
   private Integer typeLayer;
   /** 排序号
    * 
    * @pdOid 79b77710-457b-4143-8cbf-3b2504334096 */
   @Column(name = "type_order")
   private Integer typeOrder;
   /** 门类描述
    * 
    * @pdOid fbd56907-e045-4a68-81ee-888affa13318 */
   @Column(name = "type_desc", length = 1000)
   private String typeDesc;


}