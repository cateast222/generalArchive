package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.EntDO;
import com.ebs.platform.business.domain.EntDeptDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:50
 */
public interface IEntDeptDao extends JpaRepository<EntDeptDO,String>,JpaSpecificationExecutor<EntDeptDO> {

//    @Query(value="select max(sort)+1 from tb_ent_dept where ent_id = ?",nativeQuery=true)
//    Optional<Integer> maxSort(String entId);SqlServer

    @Query(value="select max(sort)+1 from tb_ent_dept where ent_id = ?",nativeQuery=true)
    Integer maxSort(String entId);// MySql

    List<EntDeptDO> queryAllByEnterpriseAndDeletedOrderBySort(EntDO ent,boolean isDelete);
}
