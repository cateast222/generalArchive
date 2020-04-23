package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 企业实体
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 12:05
 */
@Entity(name = "tb_ent")
public class EntDO extends BaseEntity {
    /**
     * 企业名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 父级ID,默认值为空
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private EntDO parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntDO getParent() {
        return parent;
    }

    public void setParent(EntDO parent) {
        this.parent = parent;
    }


    @Override
    public String toString() {
        return "EntDO{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
