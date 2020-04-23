package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.ArchiveText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface IArchiveTextDao  extends JpaRepository<ArchiveText,Integer>,JpaSpecificationExecutor<ArchiveText>{
    @Query(value = "SELECT\n" +
            "\ta.id,\n" +
            "\ttitle,\n" +
            "\tarchive_code as archiveCode,\n" +
            "\ttype_name as typeName,\n" +
            "\tarchive_text as archiveText,\n" +
            "\tarchive_id as archiveId \n" +
            "FROM\n" +
            "\tarchive_text a\n" +
            "LEFT JOIN archives b ON a.archive_id = b.id and b.is_deleted = false\n" +
            "LEFT JOIN archive_type c ON b.archive_type = c.id and c.is_deleted = false " +
            "where a.is_deleted = false AND a.archive_text LIKE :text and (if(:type >0 ,c.id = :type or c.parent_code = :type,1=1) ) order by b.id limit  :page ,:pageSize"
            ,nativeQuery = true)
    List<Map> queryArchiveText(@Param("text") String text, @Param("page")Integer page, @Param("pageSize")Integer pageSize,@Param("type")Integer type );

    @Query(value = "SELECT\n" +
            "\tcount(*) num\n" +
            "FROM\n" +
            "\tarchive_text a\n" +
            "LEFT JOIN archives b ON a.archive_id = b.id and b.is_deleted = false\n" +
            "LEFT JOIN archive_type c ON b.archive_type = c.id and c.is_deleted = false " +
            "where a.is_deleted = false AND a.archive_text LIKE :text and (if(:type >0 ,c.id = :type or c.parent_code = :type,1=1) )",nativeQuery = true)
    long queryArchiveTextCount(@Param("text") String text,@Param("type") Integer type);

    ArchiveText findByArchiveIdAndDeleted(Integer archiveId,boolean isDeleted);

}
