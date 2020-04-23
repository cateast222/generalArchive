package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.NativePlaceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface INativePlaceDao extends JpaRepository<NativePlaceDO,String>, JpaSpecificationExecutor<NativePlaceDO> {
    NativePlaceDO findByIdAndDeleted(String id, boolean b);
}
