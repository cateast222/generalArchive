package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.ArchiveType;
import com.ebs.platform.core.conf.ExtJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/4.
 */
public interface ArchiveTypeDao extends ExtJpaRepository<ArchiveType,Integer>,JpaSpecificationExecutor<ArchiveType> {

    List<ArchiveType> queryAllByDeleted(boolean isDeleted);

    @Query(value = "select id,type_Name typeName,parent_Code parentCode,type_Name label,parent_name parentName from archive_type where is_deleted=? and type_layer is null",nativeQuery = true)
    List<Map> queryAllByDeletedAndTypeLayerIsNull(boolean b);

    @Query(value = "select id,CONCAT_WS(\"/\",parent_name,type_Name) title,parent_Code parentCode,type_Name label,parent_name parentName,type_layer typeLayer  from archive_type where is_deleted=? and type_layer is not null",nativeQuery = true)
    List<Map> queryAllByDeletedAndTypeLayerIsNotNull(boolean b);

    List<ArchiveType> queryAllByDeletedAndTypeLayerOrTypeLayerIsNull(boolean b, Integer typeLayer);
}
