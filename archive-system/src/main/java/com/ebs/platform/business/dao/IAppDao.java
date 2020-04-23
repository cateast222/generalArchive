package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AccountDO;
import com.ebs.platform.business.domain.AppDO;
//import com.sun.jersey.json.impl.provider.entity.JSONArrayProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:44
 */
public interface IAppDao extends JpaRepository<AppDO,String>,JpaSpecificationExecutor<AppDO> {

     List<AppDO> queryAppDOByDeletedAndAndOwnerUser(boolean isDelete, AccountDO accountDO);

     List<AppDO> queryAllByDeleted(boolean isDelete);

	AppDO queryByIdAndDeleted(String appId, boolean b);

}
