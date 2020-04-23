package com.ebs.platform.business.service;

import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.ArchiveRoomByAdd;
import com.ebs.platform.core.dto.RequestParamDTO;

import java.util.List;
import java.util.Map;

/**
 * @author lwy
 * @Date 2019-06-06 11:39
 */
public interface IArchiveRoomService {

    Integer add(ArchiveRoomByAdd roomByAdd);

    void update(ArchiveRoomByAdd roomDTO);

    void remove(Integer id);

    Object findAll();

    /**
     * 也是查询所有，只不过设置了虚拟的第四级（格子级别）
     * @return
     */
    Object findAllBySelect();

    List<Map> findUseById(Integer id);

    void archiveMove(List<Archives> archives);
}
