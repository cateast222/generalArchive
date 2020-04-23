package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.DocnumberFormat;
import com.ebs.platform.core.conf.ExtJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by liujie on 2019/11/17.
 */
public interface DocnumberFormatDao extends ExtJpaRepository<DocnumberFormat,Integer>,JpaSpecificationExecutor<DocnumberFormat> {

    List<DocnumberFormat> findByDeletedAndArchiveTypeAndArchiveLevel(boolean isDeleted,Integer archiveType,Integer archiveLevel);
}
