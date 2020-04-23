package com.ebs.platform.business.dao.professiondao;


import com.ebs.platform.business.domain.professionentity.ArchiveIdentify;
import com.ebs.platform.business.dto.archive.ArchiveAuditDTO;
import com.ebs.platform.core.conf.ExtJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Map;

public interface ArchiveIdentifyDao extends ExtJpaRepository<ArchiveIdentify,Integer>, JpaSpecificationExecutor<ArchiveIdentify> {

    Page<ArchiveAuditDTO> findBySearcherText(Map<String,Object> entity, Pageable pageable);

    Page<ArchiveAuditDTO> querySearcherFilter(ArchiveAuditDTO archiveAuditDTO, Pageable pageable);



}
