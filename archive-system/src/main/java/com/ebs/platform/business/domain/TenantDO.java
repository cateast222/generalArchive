package com.ebs.platform.business.domain;

import com.ebs.platform.core.enums.TenantTypeEnum;
import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.enums.TenantTypeEnumConverter;

import javax.persistence.*;
import java.util.Date;

/**
 * 租赁关系实体
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 9:36
 */
@Entity(name = "tb_tenant")
public class TenantDO extends BaseEntity {
    /**
     * 应用ID
     */
    @ManyToOne
    @JoinColumn(name = "appId",nullable = false)
    private AppDO app;

    /**
     * 租赁开始日期
     */
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    /**
     * 租赁结束日期
     */
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    /**
     * 租户类型
     */
    @Column(name = "tenant_type", nullable = false)
    @Convert(converter = TenantTypeEnumConverter.class)
    private TenantTypeEnum tenantType;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "tb_user_tenant_relation",
//            joinColumns = @JoinColumn(name = "tenant_Id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
//    private List<TenantDO> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "entId",nullable = false)
    private EntDO enterprise;

    @ManyToOne
    @JoinColumn(nullable = false,name = "adminUserId")
    private AccountDO adminUser;

//    public List<TenantDO> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<TenantDO> users) {
//        this.users = users;
//    }

    public AppDO getApp() {
        return app;
    }

    public void setApp(AppDO app) {
        this.app = app;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TenantTypeEnum getTenantType() {
        return tenantType;
    }

    public void setTenantType(TenantTypeEnum tenantType) {
        this.tenantType = tenantType;
    }

    public AccountDO getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AccountDO adminUser) {
        this.adminUser = adminUser;
    }

    public EntDO getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EntDO enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public String toString() {
        return "TenantDO{" +
                "app=" + app +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tenantType=" + tenantType +
                ", enterprise=" + enterprise +
                ", adminUser=" + adminUser +
                '}';
    }
}
