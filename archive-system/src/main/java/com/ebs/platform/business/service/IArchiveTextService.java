package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.archivetext.ArchiveSearcherTextDTO;
import com.ebs.platform.business.dto.archivetext.ArchiveTextDTO;
import com.ebs.platform.business.dto.archivetext.ArchiveTextSaveDTO;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import java.util.List;
import java.util.Map;


public interface IArchiveTextService {
    QueryResponse<List<ArchiveTextDTO>> queryArchiveText(QueryRequest<ArchiveSearcherTextDTO> text);

    Map<String,Object> queryByFileId(Integer id);

    void save(ArchiveTextSaveDTO saveDTO);
}
