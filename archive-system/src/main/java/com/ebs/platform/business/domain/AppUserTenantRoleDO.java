package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;

import javax.persistence.*;

@Entity(name = "tb_user_tenant_role_ref")
public class AppUserTenantRoleDO  extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private AccountDO user;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private AppRoleDO role ;

    @ManyToOne
    @JoinColumn(name = "tenant_id",nullable = false)
    private TenantDO tenant;

    public AccountDO getUser() {
        return user;
    }

    public void setUser(AccountDO user) {
        this.user = user;
    }

    public AppRoleDO getRole() {
        return role;
    }

    public void setRole(AppRoleDO role) {
        this.role = role;
    }

    public TenantDO getTenant() {
        return tenant;
    }

    public void setTenant(TenantDO tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return "AppUserTenantRoleDO{" +
                "user=" + user +
                ", role=" + role +
                ", tenant=" + tenant +
                '}';
    }
}
