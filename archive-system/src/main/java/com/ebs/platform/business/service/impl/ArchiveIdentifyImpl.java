package com.ebs.platform.business.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.ebs.platform.business.dao.professiondao.ArchiveIdentifyDao;
import com.ebs.platform.business.dao.professiondao.ArchiveIdentifyDaoImpl;
import com.ebs.platform.business.domain.professionentity.ArchiveIdentify;
import com.ebs.platform.business.dto.archive.ArchiveAuditDTO;
import com.ebs.platform.business.mapper.*;
import com.ebs.platform.business.myutil.MapToDTO;
import com.ebs.platform.business.service.ArchiveIdentifyService;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 档案鉴定
 * @author liujie
 * @date 2019/11/28
 */
@Service
public class ArchiveIdentifyImpl extends BaseServiceImpl implements ArchiveIdentifyService {

    @Autowired
    private ArchiveIdentifyDao archiveIdentifyDao;
    @Autowired
    private ArchiveIdentifyMapper archiveIdentifyMapper;

    @Override
    public Integer saveArchiveIdentify(ArchiveAuditDTO archiveIdentifyDTO) {
//        ArchiveIdentify to = archiveIdentifyMapper.to(archiveIdentifyDTO);
//        ArchiveIdentify archiveIdentify = archiveIdentifyDao.dynamicSave(archiveIdentifyDTO.getId(), to);
//        return archiveIdentify.getId();

        return null;
    }

    @Override
    public Integer deleteArchiveIdentify(Integer id) {
        ArchiveIdentify one = archiveIdentifyDao.getOne(id);
        one.setDeleted(false);
        ArchiveIdentify archiveIdentify = archiveIdentifyDao.dynamicSave(id, one);
        return archiveIdentify.getId();
    }

    @Override
    public QueryResponse<List<ArchiveAuditDTO>> queryAllByDeleted(QueryRequest<ArchiveAuditDTO> request) {
        String searchText = request.getSearchText();
        Map<String,Object> mapEntity= new HashMap<>();
        mapEntity.put("text",searchText);

        Page<ArchiveAuditDTO> bySearcherText ;
        List<ArchiveAuditDTO> content ;
        if(!StringUtils.isEmpty(searchText)) { //搜索框查询
            bySearcherText = archiveIdentifyDao.findBySearcherText(mapEntity, request.toPageable());
            content = bySearcherText.getContent();
        }else{ //过滤查询
            ArchiveAuditDTO auditDTO = request.getCondition();
            if(auditDTO == null) auditDTO = new ArchiveAuditDTO();
            bySearcherText = archiveIdentifyDao.querySearcherFilter(auditDTO, request.toPageable());
            content = bySearcherText.getContent();
        }
        QueryResponse queryResponse = new QueryResponse(bySearcherText, content);

        return queryResponse;
    }
}
