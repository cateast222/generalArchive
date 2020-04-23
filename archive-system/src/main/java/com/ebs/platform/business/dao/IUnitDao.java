package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.UnitDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author lwy
 */

public interface IUnitDao extends JpaRepository<UnitDO,String>,JpaSpecificationExecutor<UnitDO>{

    UnitDO findByIdAndDeleted(String id, boolean b);

    List<UnitDO> findByDeleted(boolean b);

    List<UnitDO> findByDeletedAndUnitClassName(boolean b,String name);

  List<UnitDO> findByDeletedAndUnitClassNameOrDictValueId(boolean b, String className, String className1);

    List<UnitDO> findByDeletedAndDictValueId(boolean b, String className);

    List<UnitDO> findByUnitClassNameOrDictValueIdAndDeleted(String className, String className1, boolean b);

    List<UnitDO> findByDeletedAndIdIn(boolean b, List<String> unitIds);


}
