package com.ebs.platform.core.dto;

/**
 * 模块/菜单
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:37
 */
public class ModuleDTO {
    @Override
    public String toString() {
        return "ModuleDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", type='" + type + '\'' +
                ", parentId='" + parentId + '\'' +
                ", url='" + url + '\'' +
                ", remark='" + remark + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }

    private String id;
    private String name;
    private String icon;
    private String type;
    private String parentId;
    private String url;
    private String remark;
    private Integer orderNo;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}
