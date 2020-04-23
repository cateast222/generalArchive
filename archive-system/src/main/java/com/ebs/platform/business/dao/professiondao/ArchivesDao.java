package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.ArchiveRoomParamDTO;
import com.ebs.platform.business.dto.archive.OcrArchivesDTO;
import com.ebs.platform.core.conf.ExtJpaRepository;
import com.ebs.platform.core.query.QueryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/13.
 */
public interface ArchivesDao extends ExtJpaRepository<Archives,Integer>,JpaSpecificationExecutor<Archives> {

    List<Archives> queryAllByDeletedAndArchiveTypeAndArchiveStatusNot(boolean isDeleted, String archiveType,Integer archiveStatus);

    @Query(value="select id value,entity_name title,parent_code parentId from entity_type where entity_type.is_deleted=false ",nativeQuery=true)
    List<Map> queryEntityTypeSelectData();

    List<Archives> findByDeletedAndIdIn(boolean b, List<Integer> ids);

    Page<Archives> findByArchiveTypeAndArchiveLevelAndArchiveStatusOrArchiveCodeLikeOrArchiveFondsLikeOrTitleLikeOrArchiveTimeLike(String archiveType, Integer archiveLevel,Integer archiveStatus, String searchText, String searchText1, String searchText2, String searchText3, Pageable pageable);

    Page<Archives> findByArchiveTypeAndArchiveLevelAndArchiveStatus(String archiveType, Integer archiveLevel,Integer archiveStatus, Pageable pageable);

    List<Archives> queryAllById(Integer id);

   /* @Query(value = "select distinct archives.id,archives.create_time,archives.update_time,archives.update_man,archives.operator,archives.is_deleted,archives.archive_type,archives.parent_id,\n" +
            "archives.file_url,archives.archive_code,archives.archive_fonds,archives.entity_type,archives.organization_code,archives.title,archives.archive_cabinet_id,archives.archive_row,\n" +
            "archives.archive_column,archives.archive_time,archives.time_limit,archives.archive_level,archives.archive_status,archives.c1,archives.c2,archives.c3,archives.c4,archives.c5,\n" +
            "archives.c6,archives.c7,archives.c8,archives.c9,archives.c10,archives.c11,archives.c12,archives.c13,archives.c14,archives.c15,archives.c16,archives.c17,archives.c18,archives.c19,\n" +
            "archives.c20,archives.c21,archives.c22,archives.c23,archives.c24,archives.c25,archives.c26,archives.c27,archives.c28,archives.c29,archives.c30,\n" +
            "archives.c31,archives.c32,archives.c33,archives.c34,archives.c35,archives.`storage`,archives.entity_type_code,archives.fonds_code,\n" +
            "archives.number_of_pages from archives left join archive_file f on archives.id=f.archive_id where archives.is_deleted=false and f.is_deleted=false and (archives.archive_type=?1 or archives.archive_code like %?2% or archives.title like %?3%)",
            countQuery = "select count(distinct archives.id) from archives  left join archive_file f on archives.id=f.archive_id where archives.is_deleted=false and f.is_deleted=false and (archives.archive_type=?1 or archives.archive_code like %?2% or archives.title like %?3%)",nativeQuery = true)
    Page<Archives> findOcrArchives(String archvieType, String archvieCode, String title, Pageable pageable);

    @Query(value = "select distinct archives.id,archives.create_time,archives.update_time,archives.update_man,archives.operator,archives.is_deleted,archives.archive_type,archives.parent_id,\n" +
            "archives.file_url,archives.archive_code,archives.archive_fonds,archives.entity_type,archives.organization_code,archives.title,archives.archive_cabinet_id,archives.archive_row,\n" +
            "archives.archive_column,archives.archive_time,archives.time_limit,archives.archive_level,archives.archive_status,archives.c1,archives.c2,archives.c3,archives.c4,archives.c5,\n" +
            "archives.c6,archives.c7,archives.c8,archives.c9,archives.c10,archives.c11,archives.c12,archives.c13,archives.c14,archives.c15,archives.c16,archives.c17,archives.c18,archives.c19,\n" +
            "archives.c20,archives.c21,archives.c22,archives.c23,archives.c24,archives.c25,archives.c26,archives.c27,archives.c28,archives.c29,archives.c30,\n" +
            "archives.c31,archives.c32,archives.c33,archives.c34,archives.c35,archives.`storage`,archives.entity_type_code,archives.fonds_code,\n" +
            "archives.number_of_pages from archives  left join archive_file f on archives.id=f.archive_id where archives.is_deleted=false and f.is_deleted=false",
            countQuery = "select count(distinct archives.id) from archives  left join archive_file f on archives.id=f.archive_id where archives.is_deleted=false and f.is_deleted=false",nativeQuery = true)
    Page<Archives> findAllByDeletedAndContainsFile(boolean b, Pageable pageable);*/

    Page<OcrArchivesDTO> findAllByDeletedAndContainsFile2(QueryRequest<OcrArchivesDTO> req);

    @Query(value = "select case archive_level when 1 then '项目级' when 2 then '案卷级' else '文件级' end as archiveLevel,a.id,a.parent_id as parentId,f.code as archiveFonds,\n" +
            "title,archive_time as archiveTime,time_limit as timeLimit,'' as children from archives a \n" +
            "left join fonds f on a.archive_fonds=f.id and f.is_deleted=false \n" +
            "left join tb_dict_value dict on a.time_limit=dict.id and dict.is_deleted=false \n" +
            "where a.is_deleted=false and a.archive_type=? \n",nativeQuery = true)
    List<Map> queryArchives(int archiveType);

    List<Object[]> queryRoomArchives(ArchiveRoomParamDTO archiveRoomParamDTO);
}
