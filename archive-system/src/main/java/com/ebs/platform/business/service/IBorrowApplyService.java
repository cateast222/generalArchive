package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.archive.ArchiveBorrowByFind;
import com.ebs.platform.business.dto.archive.BorrowApplyDto;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;

import java.util.List;

public interface IBorrowApplyService {

    String add(BorrowApplyDto borrowApplyDto);

    QueryResponse<List<BorrowApplyDto>> queryByFilter(QueryRequest<ArchiveBorrowByFind> req);

    BorrowApplyDto queryById(Integer id);

    //借阅归还
    void doReturn(Integer id);

    void remove(Integer id);

    void reminderReturn(Integer id);
}
