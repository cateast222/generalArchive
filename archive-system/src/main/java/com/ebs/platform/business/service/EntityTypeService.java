package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.archive.EntityTypeDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/4.
 * 实体分类
 */
public interface EntityTypeService {

    /**
     * 保存实体分类
     * @param entityType
     * @return
     */
    Integer saveEntityType(EntityTypeDTO entityType);

    /**
     * 删除实体分类
     * @param id
     * @return
     */
    Integer deleteEntityType(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    EntityTypeDTO queryById(Integer id);

    /**
     * 查询所有
     * @return
     * @param id
     */
    List<Map> queryAll(Integer id);

    /**
     * 查询所有父节点
     * @return
     * @param entityFonds
     */
    List<Map> queryEntityTypeSelectData(EntityTypeDTO entityFonds);
}
