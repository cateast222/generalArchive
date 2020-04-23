package com.ebs.platform.core.dto;

/**
 * 字典值
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:02
 */
@Deprecated
public class DictValueDTO {
    @Override
    public String toString() {
        return "DictValueDTO{" +
                "id='" + id + '\'' +
                ", appDictId='" + appDictId + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    private String id;
    private String appDictId;
    private String name;
    private String code;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppDictId() {
        return appDictId;
    }

    public void setAppDictId(String appDictId) {
        this.appDictId = appDictId;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
