package com.ebs.platform.core.dto;

import com.ebs.platform.core.enums.ScopeEnum;

@Deprecated
public class DictionaryObject {

    private String code;

    private String name;

    private String remark;

    private ScopeEnum scope;

    public DictionaryObject(){}

    public DictionaryObject(String code, String name,ScopeEnum scope,String remark){
        this.code=code;
        this.name=name;
        this.scope=scope;
        this.remark=remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ScopeEnum getScope() {
        return scope;
    }

    public void setScope(ScopeEnum scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "DictionaryObject{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", scope=" + scope +
                '}';
    }
}
