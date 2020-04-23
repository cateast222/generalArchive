package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.TableConfig;
import com.ebs.platform.core.conf.ExtJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/4.
 */
public interface TableConfigDao extends ExtJpaRepository<TableConfig,Integer>,JpaSpecificationExecutor<TableConfig> {

    List<TableConfig> queryAllByDeletedAndTypeCodeAndTypeLevel(boolean isDeleted,Integer typeCode,Integer typeLevel);

    @Query(value = "select column_code name,column_name displayName,column_type dataType,column_dict columnDict,column_component columnComponent " +
            "from tableconfig \n" +
            "where type_code=?1 and type_level=?2 and query_show=?3" ,nativeQuery = true)
    List<Map> queryConditionFormQuery(Integer typeCode,Integer typeLevel,Integer queryShow);

    @Query(value = "select column_code name,column_name displayName,column_typecode dataType,column_dict columnDict,column_component columnComponent " +
            "from tableconfig \n" +
            "where type_code=?1 and type_level=?2 and list_show=?3" ,nativeQuery = true)
    List<Map> queryConditionFormList(Integer typeCode,Integer typeLevel,Integer listShow);

    @Query(value = "select column_code id,column_name name from tableconfig where type_code=?1 and type_level=?2 " ,nativeQuery = true)
    List<Map> queryDocNumberData(Integer typeCode, Integer typeLevel);
}
