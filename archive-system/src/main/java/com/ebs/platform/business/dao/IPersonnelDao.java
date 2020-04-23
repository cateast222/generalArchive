package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.EntDO;
import com.ebs.platform.business.domain.EntDeptDO;
import com.ebs.platform.business.domain.PersonnelDO;
import com.ebs.platform.core.dto.RequestParamDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 10:51
 */
public interface IPersonnelDao extends JpaRepository<PersonnelDO,String>,JpaSpecificationExecutor<PersonnelDO> {

    @Query(value="select max(sort)+1 from tb_personnel where ent_id = ?",nativeQuery=true)
    Optional<Integer> maxSort(String entId);

    Page<PersonnelDO> queryAllByDeptAndDeletedOrderBySort(EntDeptDO dept, boolean isDelete, Pageable pageable);

    Page<PersonnelDO> queryAllByEnterpriseAndDeletedAndNameOrTelOrderBySort(EntDO ent,boolean isDelete,String name,String tel, Pageable pageable);

	PersonnelDO queryByDeletedAndUserId(boolean b, String userId);

	List<PersonnelDO> queryByDeletedAndUserIdIn(boolean b, List<String> userIds);

    PersonnelDO findByIdAndDeleted(String approvalName, boolean b);
}
