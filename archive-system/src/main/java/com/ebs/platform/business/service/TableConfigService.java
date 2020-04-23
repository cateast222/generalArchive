package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.archive.TableConfigDTO;
import com.ebs.platform.business.dto.archive.TableConfigParamDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/4.
 * 档案分类元数据
 */
public interface TableConfigService {

    /**
     * 保存档案分类元数据
     * @param tableConifgDTO
     * @return
     */
    Integer saveTableConfig(TableConfigDTO tableConifgDTO);

    /**
     * 删除元数据
     * @param id
     * @return
     */
    Integer deleteTableConfig(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    TableConfigDTO queryById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<TableConfigDTO> queryAllByFilter(TableConfigDTO tableConifgDTO);

    TableConfigDTO queryTableConfigById(Integer id);

    /**
     * 新增和修改的动态表单需要的字段
     * @param tableConifgDTO
     * @return
     */
    List<TableConfigDTO> queryFormByCondition(TableConfigDTO tableConifgDTO);

    /**
     * 查询条件表单需要的字段
     * @param archivesQueryParamDTO
     * @return
     */
    List<Map> queryConditionForm(TableConfigDTO archivesQueryParamDTO);

    List<Map> queryDocNumberData(TableConfigParamDTO tableConfigParamDTO);
}
