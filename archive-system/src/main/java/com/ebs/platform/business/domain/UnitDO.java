package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseAuditingEntity;
import com.ebs.platform.core.BaseTenantEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

/**
 * 单位
 * @author lwy
 * @Date 2019-05-31 11:48
 */
@Data
@Entity(name = "tb_unit")
@DynamicInsert
@DynamicUpdate
public class UnitDO extends BaseAuditingEntity {

    /**
     * 所属单位分类，就用字典去做，添加一个字典叫 单位分类，unitClassValue就是字典值value
     */
    private String unitClassName;

    private String dictValueId;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 简称
     */
    private String simpleName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 上报名称
     */
    private String reportName;

    /**
     * 上报代码
     */
    private String reportCode;

    /**
     * 备注
     */
    private String remark;
}
