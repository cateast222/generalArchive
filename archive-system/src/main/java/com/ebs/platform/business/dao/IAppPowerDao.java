package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.domain.AppPowerDO;
import com.ebs.platform.core.enums.PowerTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:47
 */
public interface IAppPowerDao extends JpaRepository<AppPowerDO,String>,JpaSpecificationExecutor<AppPowerDO> {

//	@Query(value="select max(sort)+1 from tb_app_power where app_id = ?",nativeQuery=true)
//	Optional<Integer> maxSort(String appId);  SqlServer
// BigInteger cannot be cast to java.lang.Integer  如果是mysql会出这样的错误

  @Query(value="select max(sort)+1 from tb_app_power where app_id = ?",nativeQuery=true)
	Integer maxSort(String appId);

	List<AppPowerDO> queryAllByAppAndDeletedOrderBySortAsc(AppDO appDO,boolean isDelete);

	List<AppPowerDO> queryAllByAppAndDeletedAndPowerTypeInOrderBySortAsc(AppDO appDO,boolean isDelete,PowerTypeEnum[] types);

}
