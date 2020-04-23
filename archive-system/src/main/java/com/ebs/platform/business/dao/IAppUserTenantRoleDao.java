package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppUserTenantRoleDao extends JpaRepository<AppUserTenantRoleDO,String> {

    List<AppUserTenantRoleDO> queryAllByUserAndTenant(AccountDO user, TenantDO tenantDO);

    List<AppUserTenantRoleDO> queryAllByRoleAndTenant(AppRoleDO role,TenantDO tenantDO);

    void deleteByUser(AccountDO user);
}
