package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 应用扩展权限实体（存储应用微服务提供的权限列表，平台提供授权功能）
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 15:34
 */
@Entity(name = "tb_app_power_ext")
public class AppPowerExtDO extends BaseEntity {

    /**
     * 应用ID
     */
    @ManyToOne
    @JoinColumn(name = "appId",nullable = false)
    private AppDO app;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 微服务提供的权限列表接口地址
     */
    @Column(name = "url", nullable = false, length = 100)
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AppDO getApp() {
        return app;
    }

    public void setApp(AppDO app) {
        this.app = app;
    }

    @Override
    public String toString() {
        return "AppPowerExtDO{" +
                "app=" + app +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
