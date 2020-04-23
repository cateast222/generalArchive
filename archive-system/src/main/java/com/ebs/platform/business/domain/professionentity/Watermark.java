package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  Watermark.java
 * Author:  liuji
 * Purpose: Defines the Class Watermark
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/** 水印方案
 * 
 * @pdOid ab216732-2542-4226-ab95-834d869c3e61 */
@Data
@Entity(name = "watermark")
public class Watermark extends BaseAutoIncrementEntity{

   /** 水印编码
    * 
    * @pdOid efc2d3db-5187-4939-873b-c95d99216d3b */
   @Column(name = "watermark_code", length = 100)
   private String watermarkCode;

   /** 方案名称
    * 
    * @pdOid d8db5f87-e750-4782-bcb0-a1fbe54f0d83 */
   @Column(name = "watermark_name", length = 100)
   private String watermarkName;

   /** 水印文字
    * 
    * @pdOid 5babb498-afb4-4c8c-8bb1-068f04d09a52 */
   @Column(name = "watermark_word")
   private String watermarkWord;

   /** 字体
    * 
    * @pdOid 5f129d36-989b-476f-8192-8c2180b4dad6 */
   @Column(name = "word_type", length = 100)
   private String wordType;

   /** 字体大小
    * 
    * @pdOid 52d840c2-b357-4260-bb11-bf37d54cf3d0 */
   @Column(name = "word_size", length = 100)
   private String wordSize;

   /** 透明度
    * 
    * @pdOid 405b954e-e58e-4c15-8afa-ddb87e9b57cb */
   @Column(name = "transparency")
   private Integer transparency;

   /** 倾斜度
    * 
    * @pdOid 1d320278-b1d0-4a79-aab9-85bd5e9e15b4 */
   @Column(name = "gradient")
   private Integer gradient;

   /** 文字样式(位置)
    * 
    * @pdOid 66ae30b5-66dc-464a-a780-79a5c76db468 */
   @Column(name = "word_style", length = 100)
   private String wordStyle;

   /** 是否默认添加水印(1:是，0:否)
    * 
    * @pdOid e73b93a6-d201-4d27-ad54-4b02b9782ded */
   @Column(name = "watermark_auto_add")
   private Integer watermarkAutoAdd;


}