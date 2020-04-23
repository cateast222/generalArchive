package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.BorrowApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IBorrowApplyDao extends JpaRepository<BorrowApply,Integer>, JpaSpecificationExecutor<BorrowApply> {

    Page<BorrowApply> findByDeletedAndBorrowerContainingAndUserNameContaining(Boolean b, String s1,String s2,Pageable toPageable);

    BorrowApply findByIdAndDeleted(Integer id, boolean b);
}
