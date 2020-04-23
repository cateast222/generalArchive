package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.ArchiveBorrowItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IArchiveBorrowItemDao extends JpaRepository<ArchiveBorrowItem,Integer>, JpaSpecificationExecutor<ArchiveBorrowItem> {
}
