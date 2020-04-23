package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  TableConfig.java
 * Author:  liuji
 * Purpose: Defines the Class TableConfig
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 表设置，描述表元素的表
 * 
 * @pdOid 399cfcdc-6151-48af-9ace-abc98ac9223d */
@Data
@Entity(name = "tableconfig")
public class TableConfig extends BaseAutoIncrementEntity{

   /** 字段名称
    * 
    * @pdOid 061c22f2-15d9-495f-9fd0-d8728e94799a */
   @Column(name = "column_name", length = 100)
   private String columnName;
   /** 字段编码
    * 
    * @pdOid 5f3bb7e3-cb02-4897-9a70-a3b79753fbd2 */
   @Column(name = "column_code", length = 100)
   private String columnCode;
   /** 字段类型
    * 
    * @pdOid ba7bd707-ee54-4b08-90ea-eed4035ce811 */
   @Column(name = "column_type", length = 100)
   private String columnType;
   /** 字段类型编码
    * 
    * @pdOid ad79b65d-d03f-4788-bb7d-fd18eb96c9c2 */
   @Column(name = "column_typecode", length = 100)
   private String columnTypecode;
   /** 关联类别编码
    * 
    * @pdOid 38f7e02a-b5b2-427b-9b74-ad78c88b2766 */
   @Column(name = "type_code")
   private Integer typeCode;
   /** 关联门类级别
    *
    * @pdOid 38f7e02a-b5b2-427b-9b74-ad78c88b2766 */
   @Column(name = "type_level")
   private Integer typeLevel;
   /** 最小长度
    * 
    * @pdOid 79135055-4a8e-40dc-a467-862df2c05a37 */
   @Column(name = "min_length")
   private Integer minLength;
   /** 字段长度
    * 
    * @pdOid 52843dec-8e41-4ebb-a95c-a67a266922ff */
   @Column(name = "column_length")
   private Integer columnLength;
   /** 是否主键(1:是，0:否)
    * 
    * @pdOid 2240a5a8-71e9-4e68-9724-71861933303f */
   @Column(name = "is_primary")
   private Integer isPrimary;
   /** 是否外键(1:是，0:否)
    * 
    * @pdOid 2c918f9e-138f-447f-8e60-d55da18d1f37 */
   @Column(name = "is_foreign")
   private Integer isForeign;
   /** 是否必填(1:是，0:否)
    * 
    * @pdOid 67bb724e-b1f9-4275-bc7d-2b5e73213e67 */
   @Column(name = "is_required")
   private Integer isRequired;
   /** 数据展示组件
    * 
    * @pdOid 7073435a-bcd1-44ad-9d3b-d4ae1cfdd16c */
   @Column(name = "column_component", length = 100)
   private String columnComponent;
   /** 关联数据源
    * 
    * @pdOid 5b89b0d6-b2dd-4f83-9a72-691f50913687 */
   @Column(name = "column_datasource", length = 100)
   private String columnDatasource;
   /** 关联字典
    * 
    * @pdOid 36cbd4ad-2882-4ca3-8b87-41400b56e301 */
   @Column(name = "column_dict", length = 100)
   private String columnDict;
   /** 级联数据项
    * 
    * @pdOid b72b8374-b6e4-48f1-8486-4f9186f709c8 */
   @Column(name = "cascade_data", length = 100)
   private String cascadeData;
   /** 数据限制要求
    * 
    * @pdOid c6214915-c8e9-4a3e-b9fd-809f8734e7c0 */
   @Column(name = "column_limit", length = 100)
   private String columnLimit;
   /** 默认值
    * 
    * @pdOid d2a5e197-b1b5-4b88-8f88-fddcd5fca5fe */
   @Column(name = "default_value", length = 100)
   private String defaultValue;
   /** 查询显示(1:是，0:否)
    * 
    * @pdOid 00e85151-8034-48de-8e99-f5c267b1862f */
   @Column(name = "query_show")
   private Integer queryShow;
   /** 列表显示(1:是，0:否)
    * 
    * @pdOid 715f1698-5332-4b9a-9841-acc7d2e6b032 */
   @Column(name = "list_show")
   private Integer listShow;
   /** 编辑显示(1:是，0:否)
    * 
    * @pdOid a04ea186-3e83-459b-98b7-8ad8c879627c */
   @Column(name = "edit_show")
   private Integer editShow;
   /** 查询序号
    * 
    * @pdOid 789212c4-276b-4574-bf2c-dad185760971 */
   @Column(name = "query_order")
   private Integer queryOrder;
   /** 列表序号
    * 
    * @pdOid e7b95edc-20bc-4cbb-9114-52f048e46908 */
   @Column(name = "list_order")
   private Integer listOrder;
   /** 编辑序号
    * 
    * @pdOid 1a953956-9315-41ec-8dd6-17dcbba002ff */
   @Column(name = "edit_order")
   private Integer editOrder;
   /** 档案类别
    * 
    * @pdOid 5db592ac-08db-44aa-89e4-c3d622d54d2f */
   @Column(name = "archive_type", length = 100)
   private String archiveType;


}