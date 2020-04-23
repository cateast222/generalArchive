package com.ebs.platform.core.dto;

/**
 * 角色
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:39
 */
public class RoleDTO {

    @Override
    public String toString() {
        return "RoleDTO{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    private String id;
    private String code;
    private String name;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
