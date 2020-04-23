package com.ebs.platform.core.conf;

public class BaseSettings {
    private String code;
    private String name;
    private String dictType;
    private String dictCode;
    private String dictCodeValue;


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

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictCodeValue() {
        return dictCodeValue;
    }

    public void setDictCodeValue(String dictCodeValue) {
        this.dictCodeValue = dictCodeValue;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }
}
