package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 应用角色实体
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 10:35
 */
@Data
@Entity(name = "tb_app_role")
public class AppRoleDO extends BaseEntity {
    /**
     * 应用ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appId",nullable = false)
    private AppDO app;

    /**
     * 角色名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 角色编码,在同应用中，编码应当唯一，由程序控制
     */
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    /**
     * 租户关系id，可为空，如设置租户关系id，则只有该租户才能看见该角色
     */
    @ManyToOne
    @JoinColumn(name = "tenantId")
    private TenantDO tenant;

    @JsonIgnore
    //@ManyToMany(fetch = FetchType.LAZY)
    @ManyToMany
    @JoinTable(name = "tb_role_rule_relation",
            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id",referencedColumnName = "id"))
    private List<AppRuleDO> rules;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
//    private List<AccountDO> users;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_role_power_relation",
            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "power_id",referencedColumnName = "id"))
    private List<AppPowerDO> powers;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "tb_role_")
//    private List<AccountDO> users;

}
