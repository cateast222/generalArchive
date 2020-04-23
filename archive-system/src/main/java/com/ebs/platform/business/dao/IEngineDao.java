package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.EngineDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IEngineDao extends JpaRepository<EngineDO,String>,JpaSpecificationExecutor<EngineDO> {

    @Query(value="select * from tb_app_engine a where a.is_deleted = 0 and a.app_id = ?1 and a.code = ?2",nativeQuery = true)
    List<Map> listEnginesByAppIdAndCode(String appId,String code);

    @Query(value="SELECT a.id, a.code, a.name, a.remark, a.app_id as appId, c.name AS appName, a.user_id as userId, b.user_name as userName, a.create_date as createDate FROM tb_app_engine a\n" +
            "JOIN tb_user b ON a.user_id = b.id\n" +
            "JOIN tb_app c ON a.app_id = c.id\n" +
            "where a.is_deleted = 0  and a.app_id = ?1 order by a.create_date",nativeQuery=true)
    List<Map> listEnginesByAppId(String appId);

    @Query(value="SELECT a.id, a.code, a.name, a.remark, a.app_id as appId, c.name AS appName, a.user_id as userId, b.user_name as userName, a.create_date as createDate FROM tb_app_engine a\n" +
            "JOIN tb_user b ON a.user_id = b.id\n" +
            "JOIN tb_app c ON a.app_id = c.id\n" +
            "where a.is_deleted = 0 and a.app_id like ?1 and a.code like ?2 and a.name like ?3 order by a.create_date",nativeQuery=true)
    List<Map> listAllEngines(String appId,String code,String name);
}
