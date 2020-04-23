package com.ebs.platform.business.domain.professionentity; /***********************************************************************
 * Module:  BorrowApply.java
 * Author:  liuji
 * Purpose: Defines the Class BorrowApply
 ***********************************************************************/

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.*;

/** 借阅申请表
 * 
 * @pdOid bf4670d6-60fc-4660-a751-6d4d15042021 */
@Data
@Entity(name = "borrow_apply")
public class BorrowApply extends BaseAutoIncrementEntity{

   /** 借阅时间 **/
   @Column(name = "borrow_time")
   private Date borrowTime;

   /** 预计归还时间，账号到期时间**/
   @Column(name = "return_time")
   private Date returnTime;

   /** 实际归还时间**/
   @Column(name = "actual_time")
   private Date actualTime;

   /** 实物归还状态(0:未归还 1:已归还)**/
   @Column(name = "return_status")
   private Integer returnStatus;

   /** 借阅类型(0:实物借阅，1:在线借阅)**/
   @Column(name = "borrow_type")
   private Integer borrowType;

   /** 借阅目的（字典取值）**/
   @Column(name = "borrow_purpose", length = 100)
   private String borrowPurpose;

   /** 借阅人范围(内部/外部)**/
   @Column(name = "borrow_range", length = 100)
   private String borrowRange;

   /** 借阅人**/
   @Column(name = "borrower", length = 100)
   private String borrower;

   /** 借阅账号借阅时生成的账号**/
   @Column(name = "user_name", length = 100)
   private String userName;

   /** 利用方式(字典)**/
   @Column(name = "use_pattern", length = 100)
   private String usePattern;

   /** 借阅说明**/
   @Column(name = "borrow_explain")
   private String borrowExplain;

   /** 可选可输**/
   @Column(name = "approval_name", length = 100)
   private String approvalName;

   /** 审批单URL**/
   @Column(name = "approval_form_url")
   private String approvalFormUrl;

   /** 部门**/
   @Column(name = "unit_id", length = 100)
   private String unitId;


   /**
    * 已经催还次数
    */
   private Integer reminderCount;

}