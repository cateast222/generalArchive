package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.enums.ScopeEnum;
import com.ebs.platform.core.enums.ScopeEnumConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "tb_setting")
public class AppSettingDO extends BaseEntity {

    /**
     * 所属应用ID
     */
    @ManyToOne
    @JoinColumn(name = "app_id",nullable = false)
    private AppDO app;

    /**
     * 配置范围
     */
    @Column(nullable = false)
    @Convert(converter = ScopeEnumConverter.class)
    private ScopeEnum scopeEnum;

    /**
     * 配置名称
     */
    @Column(nullable = false,length = 100)
    private String name;

    /**
     * 配置代码
     */
    @Column(nullable = false,length = 50)
    private String code;

    /**
     * 配置分类
     */
    @Column(nullable = false,length = 100)
    private String typeDict;

    /**
     * 配置值的类型
     */
    @Column(nullable = false,length = 500)
    private String valueClassType;

    /**
     * 要扩展的字典
     */
    @Column(length = 100)
    private String refTypeDict;


    /**
     * 配置值列表
     */
    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<AppSettingValueDO> appSettingValue;

}
