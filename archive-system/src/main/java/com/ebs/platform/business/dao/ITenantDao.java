package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AccountDO;
import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.domain.EntDO;
import com.ebs.platform.business.domain.TenantDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:44
 */
public interface ITenantDao extends JpaRepository<TenantDO,String>,JpaSpecificationExecutor<TenantDO> {

    TenantDO queryByEnterpriseAndApp(EntDO ent, AppDO app);

    List<TenantDO> queryAllByAppAndDeletedOrderByEndDate(AppDO app, boolean isDelete);

    List<TenantDO> queryAllByAdminUserAndDeleted(AccountDO user,boolean isDelete);

	TenantDO queryByIdAndDeleted(String tenantId, boolean b);
}
