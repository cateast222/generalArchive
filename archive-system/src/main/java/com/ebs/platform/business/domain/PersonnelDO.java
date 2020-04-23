package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.enums.SexEnum;
import com.ebs.platform.core.enums.SexEnumConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 人员实体，人员可属于企业，部门，可关联账号。
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 12:11
 */
@Data
@Entity(name = "tb_personnel")
public class PersonnelDO extends BaseEntity {

    /**
     * 所属企业ID
     */
    @ManyToOne
    @JoinColumn(name = "entId",nullable = true)
    private EntDO enterprise;

    /**
     * 所属部门ID
     */
    @ManyToOne
    @JoinColumn(name = "deptId",nullable = true)
    private EntDeptDO dept;

    /**
     * 关联账号ID
     */
    @ManyToOne
    @JoinColumn(name = "userId",nullable = true)
    private AccountDO user;

    /**
     * 人员名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 性别
     */
    @Column(name = "sex")
    @Convert(converter = SexEnumConverter.class)
    private SexEnum sex;

    /**
     * 人员电话
     */
    @Column(name = "tel", nullable = false, length = 50)
    private String tel;

    /**
     * 人员电子信箱
     */
    @Column(name = "email",length = 100)
    private String email;

    /**
     * 生日
     */
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private Integer sort;

}
