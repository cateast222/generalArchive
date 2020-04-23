package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 后台接口规则实体
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 10:52
 */
@Data
@Entity(name = "tb_app_rule")
public class AppRuleDO extends BaseEntity {

    /**
     * 应用ID
     */
    @ManyToOne
    @JoinColumn(name = "appId",nullable = false)
    private AppDO app;

    @JsonIgnore
    @ManyToMany(mappedBy = "rules",fetch = FetchType.LAZY)
    private List<AppRoleDO> roles;
    /**
     * 路径表达式，如/app/**
     */
    @Column(name = "url_pattern", nullable = false, length = 100)
    private String urlPattern;

    /**
     * 限定请求方式，如GET,POST,PUT等，为空则允许所有
     */
    @Column(name = "methods", length = 100)
    private String methods;
}
