package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.enums.PowerTypeEnum;
import com.ebs.platform.core.enums.PowerTypeEnumConverter;
import lombok.Data;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.util.List;

/**
 * 应用前台权限实体
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 10:47
 */
@Data
@Entity(name = "tb_app_power")
public class AppPowerDO extends BaseEntity {

    /**
     * 应用ID
     */
    @ManyToOne
    @JoinColumn(name = "appId",nullable = false)
    private AppDO app;

    /**
     * 权限名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 权限编码,在同应用中，编码应当唯一，由程序控制
     */
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    /**
     * 权限类别
     */
    @Column(name = "powerType",nullable = false)
    @Convert(converter = PowerTypeEnumConverter.class)
    private PowerTypeEnum powerType = PowerTypeEnum.Default;

    /**
     * 父级ID,默认值为空
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    private AppPowerDO parent;

    @ManyToMany(mappedBy = "powers",fetch = FetchType.LAZY)
    private List<AppRoleDO> roles;

    private String url;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

}
