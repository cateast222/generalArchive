package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * 应用实体
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 9:53
 */
@Entity(name = "tb_app")
@Data
public class AppDO extends BaseEntity {

    /**
     * 应用名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ownerUserId",nullable = false)
    private AccountDO ownerUser;

}
