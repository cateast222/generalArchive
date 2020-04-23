package com.ebs.platform.business.dao.professiondao;


import com.ebs.platform.business.domain.professionentity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IOrganizationDao extends JpaRepository<Organization,Integer>, JpaSpecificationExecutor<Organization> {
    @Query(value = "select * from Organization where is_deleted = false and ( org_name like %?1% or org_code like %?1% or english_name like %?1% or short_name like %?1%)",
            countQuery = "select * from Organization where is_deleted = false and ( org_name like %?1% or org_code like %?1% or english_name like %?1% or short_name like %?1%",nativeQuery = true)
    Page<Organization> findByFilter(String parameter, Pageable pageable);

    @Query(value = "select id as value,org_name as label from Organization where is_deleted = false",nativeQuery = true)
    List<Map> queryIdAndName();
}
