package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.Fonds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IFondsDao extends JpaRepository<Fonds,Integer>, JpaSpecificationExecutor<Fonds> {

    Fonds queryByDeletedAndId(boolean b, Integer id);

    List<Fonds> findByDeleted(boolean b);
    @Query(value = "select * from fonds where is_deleted = false and ( code like %?1% or name like %?2%)",
            countQuery = "select count(id) from fonds where is_deleted = false and ( code like %?1% or name like %?2%)",nativeQuery = true)
    Page<Fonds> findByNameContainingOrCodeContainingaa(String code, String name, Pageable pageable);

    Fonds findByDeletedAndCode(boolean b,String code);

    @Query(value = "select id,name FROM fonds",nativeQuery = true)
    List<Map> getIdAndName();
}
