package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "tb_setting_value")
public class AppSettingValueDO extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "app_setting_id",nullable = false)
    private AppSettingDO appSetting;

    @ManyToOne
    @JoinColumn(name = "app_id",nullable = false)
    private AppDO app;

    @ManyToOne
    @JoinColumn(name = "tenant_id",nullable = false)
    private TenantDO tenant;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private AccountDO user;

    /**
     * 配置值
     */
    @Column(nullable = false,columnDefinition = "nvarchar(max)")
    private String value;

    /**
     * 扩展字典的值
     */
    @Column(nullable = false,columnDefinition = "nvarchar(500)")
    private String refTypeDictValue;
}
