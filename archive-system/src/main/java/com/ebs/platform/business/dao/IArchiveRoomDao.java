package com.ebs.platform.business.dao;

import com.ebs.platform.business.domain.professionentity.ArchiveRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author lwy
 * @Date 2019-06-06 11:54
 */
public interface IArchiveRoomDao extends JpaRepository<ArchiveRoom,Integer>,JpaSpecificationExecutor<ArchiveRoom>{
    List<ArchiveRoom> findByDeleted(boolean b);

    @Query(value = "SELECT archive_row AS archiveRow,archive_column AS archiveColumn,COUNT(*) as amount  " +
                  " FROM archives WHERE is_deleted=0 AND archive_cabinet_id =?  GROUP BY archive_row,archive_column ",nativeQuery = true)
    List<Map> findByCabinet(Integer id);

    @Query(value = "SELECT T2.id FROM (  SELECT  @r AS _id,( SELECT @r \\:= parent_id FROM tb_archive_room WHERE id = _id ) AS parent_id, @l \\:= @l + 1 AS lvl " +
            " FROM ( SELECT @r \\:= ?1, @l \\:= 0 ) vars, tb_dict_value h  " +
            " WHERE @r <> '0'  ) T1  JOIN tb_archive_room T2 ON T1._id = T2.id WHERE is_deleted=0 ORDER BY T1.lvl DESC",nativeQuery = true)
    List<Integer> findByParent(String archiveCabinetId);

    ArchiveRoom queryByIdAndDeleted(Integer id, Boolean b);

//    @Query(nativeQuery = true,value = "SELECT max(sort)+1 FROM tb_archive_room")
//    Integer nativeFindMaxSort();

    List<ArchiveRoom> queryByParentIdAndDeleted(String id, boolean b);

    @Query(nativeQuery = true,value = "SELECT * FROM tb_archive_room WHERE FIND_IN_SET(id,getChildLst(?1))")
    List<ArchiveRoom> nativeFindChildById(String id);
}
