package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.archive.ArchiveTypeDTO;
import com.ebs.platform.business.dto.archive.ArchiveTypeRequestParamDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/4.
 * 档案类型
 */
public interface ArchiveTypeService {

    /**
     * 保存档案类型
     * @param archiveType
     * @return
     */
    Integer saveArchiveType(ArchiveTypeDTO archiveType);

    /**
     * 删除档案类型
     * @param id
     * @return
     */
    Integer deleteArchiveType(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ArchiveTypeDTO queryById(Integer id);

    /**
     * 查询所有
     * @return
     * @param archiveTypeRequestParamDTO
     */
    List<ArchiveTypeDTO> queryAll(ArchiveTypeRequestParamDTO archiveTypeRequestParamDTO);

    /**
     * 查询所有父节点
     * @return
     */
    List<Map> queryAllParent();

    /**
     * 查询所有子节点只包含具体类别
     * @return
     */
    List<Map> queryAllChildType();
}
