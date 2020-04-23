package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseAuditingEntity;
import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.BaseTenantEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * 字典值实体（树结构）
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 10:25
 */
@Data
@Entity(name = "tb_dict_value")
public class AppDictValueDO extends BaseAuditingEntity {

    /**
     * 所属字典ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dict_id", nullable = false)
    private AppDictDO dict;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appId",nullable = false)
    private AppDO app;

    /**
     * 所属租户，当字典的 scope = tenant 时，该值不能为空
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenantId")
    private TenantDO tenant;

    /**
     * 所属用户，当字典的 scope = user 时，该值不空
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private AccountDO user;

    /**
     * 值名称
     */
    @Column(name = "name",nullable = false , length = 50)
    private String name;


    /**
     * 值编码，在同一个 scope 范围内，该值应唯一，由代码控制
     */
    @Column(name = "value", length = 50,nullable = false)
    private String value;

    /**
     * 字典备注
     */
    private String remark;
    
    private String code;// 先为空，不传东西

    /**
     * 字典排序值
     */
    private Integer sort;

    /**
     * 父级值ID,默认值为空
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private AppDictValueDO parent;

}
