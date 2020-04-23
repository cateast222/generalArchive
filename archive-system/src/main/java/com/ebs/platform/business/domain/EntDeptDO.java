package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;

import javax.persistence.*;

/**
 * 企业部门实体
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 12:08
 */
@Entity(name = "tb_ent_dept")
public class EntDeptDO extends BaseEntity {

    /**
     * 所属企业ID
     */
    @ManyToOne
    @JoinColumn(name = "entId",nullable = false)
    private EntDO enterprise;

    /**
     * 部门名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 父级ID,默认值为空
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private EntDeptDO parent;

    /**
     * 序号
     */
    @Column(name = "sort",nullable = false)
    private Integer sort;

    public EntDO getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EntDO enterprise) {
        this.enterprise = enterprise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntDeptDO getParent() {
        return parent;
    }

    public void setParent(EntDeptDO parent) {
        this.parent = parent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "EntDeptDO{" +
                "enterprise=" + enterprise +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", sort=" + sort +
                '}';
    }
}
