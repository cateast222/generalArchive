package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.dto.archive.ArchiveAuditDTO;
import com.ebs.platform.business.myutil.SqlDynamicHelp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.*;


public class ArchiveIdentifyDaoImpl {

    private SqlDynamicHelp<ArchiveAuditDTO> sqlDynamicHelp = new SqlDynamicHelp(ArchiveAuditDTO.class);

    private String nativeDataSql = "SELECT\n" +
            "\ta.id,\n" +
            "\ta.identify_id identifyId,\n" +
            "\tb.keep_time keepTime,\n" +
            "\tb.identify_person identifyPerson,\n" +
            "\tb.identify_date identifyDate,\n" +
            "\tb.identify_reason identifyReason,\n" +
            "\tb.identify_explain identifyExplain,\n" +
            "\ta.audit_idea auditIdea,\n" +
            "\ta.audit_person auditPerson,\n" +
            "\ta.audit_date auditDate,\n" +
            "\ta.audit_status auditStatus,\n" +
            "\ta.audit_remark auditRemark\n" +
            "FROM\n" +
            "\tarchive_audit a\n" +
            "LEFT JOIN archive_identify b ON a.identify_id = b.id AND b.is_deleted = FALSE\n" +
            "WHERE a.is_deleted = FALSE ";
    private String nativeCountSql ="SELECT\n" +
            " count(*) " +
            "FROM\n" +
            "\tarchive_audit a\n" +
            "LEFT JOIN archive_identify b ON a.identify_id = b.id AND b.is_deleted = FALSE\n" +
            "WHERE a.is_deleted = FALSE ";

    @PersistenceContext
    private EntityManager entityManager;
    public Page<ArchiveAuditDTO> findBySearcherText(Map<String,Object> entity, Pageable page){

        String sqlWhere =" AND " +
                        "(b.keep_time LIKE:text OR\n" +
                        "\tb.identify_person LIKE :text OR\n" +
                        "\tb.identify_date LIKE :text OR\n" +
                        "\tb.identify_reason LIKE :text OR\n" +
                        "\tb.identify_explain LIKE :text OR\n" +
                        "\ta.audit_idea LIKE :text OR\n" +
                        "\ta.audit_person LIKE :text OR\n" +
                        "\ta.audit_date LIKE :text OR\n" +
                        "\ta.audit_status LIKE :text OR\n" +
                        "\ta.audit_remark LIKE :text\n" +
                        "\t)";
        Map<String,String> map = new HashMap<>();
        map.put("text",sqlWhere);
        Page<ArchiveAuditDTO> queryData = sqlDynamicHelp.getQueryData(entityManager, entity, page, nativeDataSql, nativeCountSql, map);
        return queryData;
    }

    public Page<ArchiveAuditDTO> querySearcherFilter(ArchiveAuditDTO archiveAuditDTO,Pageable pageable){

        Map<String,String> map = new HashMap<>();
        map.put("keepTime"," AND b.keep_time = :keepTime");
        map.put("identifyPerson"," AND b.identify_person like CONCAT('%',:identifyPerson,'%')");
        map.put("identifyDate"," AND b.identify_date = :identifyDate");
        map.put("identifyReason"," AND b.identify_reason like CONCAT('%',:identifyReason,'%')");
        map.put("identifyExplain"," AND b.identify_explain like CONCAT('%',:identifyExplain,'%')");
        map.put("auditIdea"," AND a.audit_idea like CONCAT('%',:auditIdea,'%')");
        map.put("auditPerson"," AND a.audit_person like CONCAT('%',:auditPerson,'%')");
        map.put("auditDate"," AND a.audit_date = :auditDate");
        map.put("auditStatus"," AND a.audit_status = :auditStatus");
        map.put("auditRemark"," AND a.audit_remark like CONCAT('%',:auditRemark,'%')");
        Page<ArchiveAuditDTO> queryData = sqlDynamicHelp.getQueryData(entityManager, archiveAuditDTO, pageable, nativeDataSql, nativeCountSql, map);
        return queryData;
    }

}
