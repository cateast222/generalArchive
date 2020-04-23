package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.domain.AppRoleDO;
import com.ebs.platform.business.domain.AppRuleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:49
 */
public interface IAppRuleDao extends JpaRepository<AppRuleDO,String>,JpaSpecificationExecutor<AppRuleDO> {

    List<AppRuleDO> queryAllByAppAndAndDeleted(AppDO app,boolean isDelete);

}
