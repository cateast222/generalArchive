package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.dto.archive.ArchiveRoomParamDTO;
import com.ebs.platform.business.dto.archive.OcrArchivesDTO;
import com.ebs.platform.core.query.QueryRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujie on 2019/12/1.
 */
public class ArchivesDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> queryRoomArchives(ArchiveRoomParamDTO archiveRoomParamDTO){
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(archiveRoomParamDTO.getTheRow())){
            sb.append("select title,t.type_name typeName,a.archive_code archiveCode from archives a left join archive_type t on a.archive_type=t.id where a.is_deleted=false and parent_id is null and archive_cabinet_id=:archiveCabinetId and archive_row=:theRow and archive_column=:theColumn");
        }else {
            sb.append("select title,t.type_name typeName,a.archive_code archiveCode from archives a left join archive_type t on a.archive_type=t.id where a.is_deleted=false and archive_type=:archvieType and parent_id is null ");
            if(StringUtils.isNotBlank(archiveRoomParamDTO.getArchiveCode())){
                sb.append("and archive_code like :archiveCode");
            }
        }
        Query nativeQuery = entityManager.createNativeQuery(sb.toString());
        if(StringUtils.isNotBlank(archiveRoomParamDTO.getTheRow())){
            nativeQuery.setParameter("theRow",archiveRoomParamDTO.getTheRow());
            nativeQuery.setParameter("theColumn",archiveRoomParamDTO.getTheColumn());
            nativeQuery.setParameter("archiveCabinetId",archiveRoomParamDTO.getArchiveCabinetId());
        }else {
            nativeQuery.setParameter("archiveType",archiveRoomParamDTO.getArchiveType());
            if(StringUtils.isNotBlank(archiveRoomParamDTO.getArchiveCode())){
                nativeQuery.setParameter("archiveCode","%"+archiveRoomParamDTO.getArchiveCode()+"%");
            }
        }
        List resultList = nativeQuery.getResultList();
        return  resultList;
    }

    public Page<OcrArchivesDTO> findAllByDeletedAndContainsFile2(QueryRequest<OcrArchivesDTO> req){
        String searchText = req.getSearchText();
        StringBuilder sb = new StringBuilder();
        StringBuilder countSb = new StringBuilder("select count(*) from ( ");
        Page page = null;
        Pageable pageable = req.toPageable();
        sb.append("select distinct t.type_Name ,archives.archive_code,archives.title  from archives  ");
        sb.append("inner join archive_file f on archives.id=f.archive_id " );
        sb.append("left join archive_type t on archives.archive_type=t.id ");
        sb.append("where archives.is_deleted=false and f.is_deleted=false ");
        if(!StringUtils.isBlank(searchText)){
            sb.append(" and (archives.archive_type=:archiveType or archives.archive_code like :archiveCode or archives.title like :title) ");
        }
        String countSql = countSb.toString() + sb.toString() + " ) temp";
        Query countQuery = entityManager.createNativeQuery(countSql);
        BigInteger total = (BigInteger) countQuery.getSingleResult();
        Query nativeQuery = entityManager.createNativeQuery(sb.toString());
        if(!StringUtils.isBlank(searchText)){
            nativeQuery.setParameter("archiveType",searchText);
            nativeQuery.setParameter("archiveCode","%"+searchText+"%");
            nativeQuery.setParameter("title","%"+searchText+"%");
        }
        nativeQuery.setFirstResult((req.getPageIndex()-1) * req.getPageSize());
        nativeQuery.setMaxResults(req.getPageSize());
        List<Object[]> resultList = nativeQuery.getResultList();
        List<OcrArchivesDTO> list = new ArrayList<>();
        if(resultList.size()>0){
            for(int i =0;i<resultList.size();i++){
                Object[] o = resultList.get(i);
                OcrArchivesDTO dto = new OcrArchivesDTO((String) o[0],(String)o[1],(String)o[2]);
                list.add(dto);
            }
        }
        Page<OcrArchivesDTO> resultPage = new PageImpl<>(list, pageable, total.longValue());
        return resultPage;
    }

}
