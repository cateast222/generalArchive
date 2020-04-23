package com.ebs.platform.core.dto;

public class AppSettingsValueDTO {
    private String value;
    private String refTypeDictValue;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getRefTypeDictValue() {
        return refTypeDictValue;
    }
    public void setRefTypeDictValue(String refTypeDictValue) {
        this.refTypeDictValue = refTypeDictValue;
    }
}