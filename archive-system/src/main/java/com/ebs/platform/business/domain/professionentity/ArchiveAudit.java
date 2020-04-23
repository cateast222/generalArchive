package com.ebs.platform.business.domain.professionentity;

import com.ebs.platform.core.BaseAutoIncrementEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/** 审核信息
 * 
 */
@Data
@Entity(name = "archive_audit")
public class ArchiveAudit extends BaseAutoIncrementEntity{

   /** 审核人*/
   @Column(name = "audit_person",length = 100)
   private String auditPerson;
   /** 审核日期 */
   @Column(name = "audit_date")
   private Date auditDate;
   /** 审核意见 */
   @Column(name = "audit_idea")
   private String auditIdea;
   /** 备注 */
   @Column(name = "audit_remark")
   private String auditRemark;
   /** 审核状态（0:驳回 1:通过） */
   @Column(name = "audit_status")
   private int auditStatus;
   /** 关联鉴定表id */
   @Column(name = "identify_id")
   private int identifyId;

}