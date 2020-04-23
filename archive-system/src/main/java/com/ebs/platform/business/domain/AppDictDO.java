package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.enums.ScopeEnum;
import com.ebs.platform.core.enums.ScopeEnumConverter;

import javax.persistence.*;

/**
 * 应用字典
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 10:13
 */
@Entity(name = "tb_dict")
public class AppDictDO extends BaseEntity {

    /**
     * 所属应用ID
     */
    @ManyToOne
    @JoinColumn(name = "appId",nullable = false)
    private AppDO app;

    /**
     * 字典名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 字典编码，在同应用中，编码应唯一，由代码控制,这里这个code 就是 key,  value 就是 dict_value 的value 字段
     */
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    /**
     * 字典的定义范围，指明由哪一些来定义具体字典值
     */
    @Column(nullable = false)
    @Convert(converter = ScopeEnumConverter.class)
    private ScopeEnum scope = ScopeEnum.Tenant;

    private String remark;

    public AppDO getApp() {
        return app;
    }

    public void setApp(AppDO app) {
        this.app = app;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ScopeEnum getScope() {
        return scope;
    }

    public void setScope(ScopeEnum scope) {
        this.scope = scope;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AppDictDO{" +
                "app=" + app +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", scope=" + scope +
                ", remark='" + remark + '\'' +
                '}';
    }
}
