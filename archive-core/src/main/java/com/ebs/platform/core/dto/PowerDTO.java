package com.ebs.platform.core.dto;

/**
 * 权限
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:37
 */
public class PowerDTO {
    @Override
    public String toString() {
        return "PowerDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", code='" + code + '\'' +
                ", url='" + url + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    private String id;
    private String name;
    private Integer type;
    private String code;
    private String url;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
