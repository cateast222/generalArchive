package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archivestatistics.StatisticsBorrowerRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ArchiveStatisticsDao extends JpaRepository<Archives,Integer>, JpaSpecificationExecutor<Archives> {

    @Query(value = "SELECT \n" +
            "\ttype_name as typeName,\n" +
            "\tSUM(if(archive_level = 2,1,0)) archivesCount,\n" +
            "\tSUM(if(archive_level = 3,1,0)) filesCount,\n" +
            "\tSUM(if(archive_level = 3 AND c.file_url IS NOT NULL,1,0)) mountCount,\n" +
            "\tSUM(if(archive_level = 2,number_of_pages,0)) archivesSize,\n" +
            "\tSUM(if(archive_level = 3,number_of_pages,0)) filesSize\n" +
            "FROM archives a LEFT JOIN archive_type b ON a.archive_type = b.id \n" +
            " LEFT JOIN archive_file c ON a.id = c.archive_id and a.archive_level = 3" +
            "\n where 1 = 1 and if(:code = '' or :code is null, 1=1 , archive_fonds = :code)" +
            " and if(:year = '' or :year is null, 1=1, archive_time = :year)" +
            " and  if( :start is null , 1=1, a.create_time >= :start)" +
            " and if(:end is null , 1=1, a.create_time <= :end)" +
            "GROUP BY a.archive_type",nativeQuery = true)
    List<Map> statisticsArchiveByFilter(@Param("code") String code, @Param("year") String year, @Param("start") Date start,@Param("end")Date end);

    @Query(value = "SELECT\n" +
            "\td.type_name typeName,\n" +
            "\tCOUNT(DISTINCT c.id) manTime,\n" +
            "\tCOUNT(*) tape\n" +
            "FROM\n" +
            "\tborrow_apply a\n" +
            "LEFT JOIN archive_borrow_item b ON a.id = b.borrow_register_id AND b.is_deleted = FALSE\n" +
            "LEFT JOIN archives c ON b.archive_item_id = c.id AND c.is_deleted = FALSE\n" +
            "LEFT JOIN archive_type d ON c.archive_type = d.id AND d.is_deleted =FALSE\n" +
            "WHERE \n" +
            "\ta.is_deleted = FALSE\n" +
            " and a.borrow_type = :borrowType" +
            " and  if( :start is null , 1=1, a.borrow_time >= :start)" +
            " and if(:end is null , 1=1, a.borrow_time <= :end)" +
            " and if(:useParttern = '',1=1,a.use_parttern = :useParttern)" +
            " and if(:borrowPurpose = '',1=1,a.borrow_purpose = :borrowPurpose)" +
            "GROUP BY d.id",nativeQuery = true)
    List<Map> statisticsBorrower(@Param("start") Date start,@Param("end") Date end ,@Param("useParttern") String useParttern,@Param("borrowPurpose") String borrowPurpose,@Param("borrowType") Integer borrowType );

    @Query(value = "SELECT\n" +
            "\td.type_name typeName,\n" +
            "\tCOUNT(DISTINCT c.id) manTime,\n" +
            "\tCOUNT(*) tape\n" +
            "FROM\n" +
            "\tborrow_apply a\n" +
            "LEFT JOIN archive_borrow_item b ON a.id = b.borrow_register_id AND b.is_deleted = FALSE\n" +
            "LEFT JOIN archives c ON b.archive_item_id = c.id AND c.is_deleted = FALSE\n" +
            "LEFT JOIN archive_type d ON c.archive_type = d.id AND d.is_deleted =FALSE\n" +
            "WHERE \n" +
            "\ta.is_deleted = FALSE\n" +
            " and a.borrow_type = :borrowType" +
            " and  if( :requestDTO.getBorrowerStart() is null , 1=1, a.borrow_time >= :requestDTO.getBorrowerStart())" +
            " and if(:requestDTO.getBorrowerEnd() is null , 1=1, a.borrow_time <= :requestDTO.getBorrowerEnd())" +
            " and if(:requestDTO.getUseParttern() = '',1=1,a.use_parttern = :requestDTO.getUseParttern())" +
            " and if(:requestDTO.getBorrowPurpose() = '',1=1,a.borrow_purpose = :requestDTO.getBorrowPurpose())" +
            "GROUP BY d.id",nativeQuery = true)
    List<Map> statisticsBorrower(@Param("requestDTO") StatisticsBorrowerRequestDTO requestDTO,@Param("borrowType") Integer borrowType );
}
