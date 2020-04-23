package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.EntityType;
import com.ebs.platform.core.conf.ExtJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/4.
 */
public interface EntityTypeDao extends ExtJpaRepository<EntityType,Integer>,JpaSpecificationExecutor<EntityType> {

    @Query(value="select  *  from entity_type where entity_type.is_deleted=?1 and entity_fonds=?2 ",nativeQuery=true)
    List<Map> queryAllByDeletedAndEntityFonds(boolean isDeleted,String entityFonds);

    @Query(value="select  id,entity_name label,parent_code parentId  from entity_type where entity_type.is_deleted=false ",nativeQuery=true)
    List<Map> queryEntityTypeSelectData();

    @Query(value="select  id,entity_name label,parent_code parentId  from entity_type where entity_type.is_deleted=false and entity_fonds=?1 and id!=?2",nativeQuery=true)
    List<Map> queryEntityTypeSelectDataByEntityFondsAndIdNot(Integer entityFonds, Integer id);

    @Query(value="select  id,entity_name label,parent_code parentId  from entity_type where entity_type.is_deleted=false and entity_fonds=?",nativeQuery=true)
    List<Map> queryEntityTypeSelectDataByEntityFonds(Integer entityFonds);
}
