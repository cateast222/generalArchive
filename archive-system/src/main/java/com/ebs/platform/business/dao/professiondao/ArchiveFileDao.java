package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.ArchiveFile;
import com.ebs.platform.core.conf.ExtJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ArchiveFileDao extends ExtJpaRepository<ArchiveFile,Integer>, JpaSpecificationExecutor<ArchiveFile> {

    List<ArchiveFile> queryByArchiveIdAndDeleted(int archiveId, boolean deleted);

    List<ArchiveFile> queryByTextIdAndDeleted(int textId, boolean deleted);

    List<ArchiveFile> findALLByDeletedAndArchiveId(boolean b, Integer archiveId);

}
