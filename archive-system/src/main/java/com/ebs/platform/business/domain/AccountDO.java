package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.enums.UserTypeEnum;
import com.ebs.platform.core.enums.UserTypeEnumConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

/**
 * 账号实体
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 9:27
 */
@Data
@Entity(name = "tb_user")
public class AccountDO extends BaseEntity  {

    @Column(name = "userName",unique=true,nullable = false , length = 100)
    private String userName;

    @Column(name = "password",nullable = false , length = 20)
    private String password;

    @Column(nullable = false)
    @Convert(converter=UserTypeEnumConverter.class)
    private UserTypeEnum userType;

    @OneToOne
    @JoinColumn(name = "currentTenantId",referencedColumnName ="id" )
    private TenantDO currentTenant;

    /**
     * 这里一对一的关系定义的有点问题，但是不影响使用
     */
    @OneToOne
    @JoinColumn(name = "personnelId",referencedColumnName = "id")
    private PersonnelDO personnel;


    @Column(nullable = false)
    private Date createdDate=new Date();


}
