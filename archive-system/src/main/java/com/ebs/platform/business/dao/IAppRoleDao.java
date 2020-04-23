package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.domain.AppRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:48
 */
public interface IAppRoleDao extends JpaRepository<AppRoleDO,String>,JpaSpecificationExecutor<AppRoleDO> {

	@Query(value="delete from tb_role_power_relation where role_id=?",nativeQuery=true)
	void deleteRoleAndPower(String roleId);

	@Query(value="INSERT INTO tb_role_power_relation(role_id,power_id) VALUES(?1,?2)",nativeQuery=true)
	void insertRoleAndPower(String roleId, String powerId);

	@Query(value="INSERT INTO tb_role_rule_relation(role_id,rule_id) VALUES(?1,?2)",nativeQuery=true)
	void insertRoleAndRule(String roleId, String ruleId);

	@Query(value="delete from tb_role_rule_relation where role_id=?",nativeQuery=true)
	void deleteRoleAndRule(String roleId);

	List<AppRoleDO> queryAllByAppAndDeleted(AppDO app, boolean isDelete);

}
