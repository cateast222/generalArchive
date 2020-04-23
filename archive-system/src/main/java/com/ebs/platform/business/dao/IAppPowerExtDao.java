package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.domain.AppPowerExtDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:48
 */
public interface IAppPowerExtDao extends JpaRepository<AppPowerExtDO,String>,JpaSpecificationExecutor<AppPowerExtDO> {
    List<AppPowerExtDO> queryByAppAndDeleted(AppDO app,boolean isDelete);
}
