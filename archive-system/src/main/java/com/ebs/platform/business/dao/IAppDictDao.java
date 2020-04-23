package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.domain.AppDictDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:46
 */
public interface IAppDictDao extends JpaRepository<AppDictDO,String>,JpaSpecificationExecutor<AppDictDO> {
    List<AppDictDO> queryAllByAppAndAndDeleted(AppDO app, boolean isDelete);

    AppDictDO queryByCode(String code);

	AppDictDO queryByCodeAndAppAndDeleted(String code, AppDO appDO, boolean b);
}
