package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.archive.ArchiveAuditDTO;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;

import java.util.List;

/**
 * Created by liujie on 2019/11/27.
 * 档案鉴定信息
 */
public interface ArchiveIdentifyService {

    /**
     * 保存档案鉴定信息
     * @param archiveIdentifyDTO
     * @return
     */
    Integer saveArchiveIdentify(ArchiveAuditDTO archiveIdentifyDTO);

    /**
     * 删除档案鉴定信息
     * @param id
     * @return
     */
    Integer deleteArchiveIdentify(Integer id);

    /**
     * 查询档案鉴定信息
     * @return
     * @param request
     */
    QueryResponse<List<ArchiveAuditDTO>> queryAllByDeleted(QueryRequest<ArchiveAuditDTO> request);


}
