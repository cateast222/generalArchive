package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.TableConfigDao;
import com.ebs.platform.business.domain.professionentity.TableConfig;
import com.ebs.platform.business.dto.archive.TableConfigDTO;
import com.ebs.platform.business.dto.archive.TableConfigParamDTO;
import com.ebs.platform.business.mapper.TableConfigMapper;
import com.ebs.platform.business.service.TableConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/7.
 */
@Service
public class TableConfigServiceImpl implements TableConfigService {

    @Autowired
    private TableConfigDao tableConfigDao;
    @Autowired
    private TableConfigMapper tableConfigMapper;

    @Override
    @Transactional
    public Integer saveTableConfig(TableConfigDTO tableConifgDTO) {
        TableConfig tableConfig = tableConfigMapper.to(tableConifgDTO);
        if (tableConfig.getId() == null) {
            //所有255长度字段
            List<String> allShotColumn = new ArrayList<>();
            for(int j =0;j<30;j++){
                allShotColumn.add("c"+(j+1));
            }
            //所有1000长度字段
            List<String> allLongColumn = new ArrayList<>();
            for(int i =30;i<35;i++){
                allLongColumn.add("c"+(i+1));
            }
            //查询已经占用的字段
            List<TableConfigDTO> tableConfigDTOS = queryAllByFilter(tableConifgDTO);
            if(tableConfigDTOS.size()>0){
                for(int j =0;j<30;j++){
                    String column = "c"+(j+1);
                    for(int i =0;i<tableConfigDTOS.size();i++){
                        if(tableConfigDTOS.get(i).getColumnCode().equals(column)){
                            //移除已经使用的短字段
                            allShotColumn.remove(column);
                        }
                    }
                }
                for(int j =30;j<35;j++){
                    String column = "c"+(j+1);
                    for(int i =0;i<tableConfigDTOS.size();i++){
                        if(tableConfigDTOS.get(i).getColumnCode().equals(column)){
                            //移除已经使用的长字段
                            allLongColumn.remove(column);
                        }
                    }
                }
            }
            //2.循环出未占用的字段并取值
            Integer columnLength = tableConfig.getColumnLength();
            if(columnLength!=null && columnLength >255){
                tableConfig.setColumnCode(allLongColumn.get(0));
            }else {
                tableConfig.setColumnCode(allShotColumn.get(0));
            }
        }
        TableConfig save = tableConfigDao.dynamicSave(tableConfig.getId(),tableConfig);
        return save.getId();
    }

    @Override
    @Transactional
    public Integer deleteTableConfig(Integer id) {
        TableConfig one = tableConfigDao.getOne(id);
        one.setDeleted(true);
        TableConfig save = tableConfigDao.save(one);
        return save.getId();
    }

    @Override
    public TableConfigDTO queryById(Integer id) {
        TableConfig one = tableConfigDao.getOne(id);
        return tableConfigMapper.from(one);
    }

    @Override
    public List<TableConfigDTO> queryAllByFilter(TableConfigDTO tableConifgDTO) {
        List<TableConfig> tableConfigs = tableConfigDao.queryAllByDeletedAndTypeCodeAndTypeLevel(false,tableConifgDTO.getTypeCode(),tableConifgDTO.getTypeLevel());
        return tableConfigMapper.from(tableConfigs);
    }

    @Override
    public TableConfigDTO queryTableConfigById(Integer id) {
        TableConfig one = tableConfigDao.getOne(id);
        return tableConfigMapper.from(one);
    }

    @Override
    public List<TableConfigDTO> queryFormByCondition(TableConfigDTO tableConifgDTO) {
        Specification<TableConfig> specification = (Specification<TableConfig>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(cb.equal(root.<Integer>get("deleted"), 0));
            if (tableConifgDTO.getTypeCode() != null) {
                predicates.add(cb.equal(root.<Integer>get("typeCode"), tableConifgDTO.getTypeCode()));
            }
            if (tableConifgDTO.getTypeLevel() != null) {
                predicates.add(cb.equal(root.<Integer>get("typeLevel"), tableConifgDTO.getTypeLevel()));
            }
            if (tableConifgDTO.getEditShow() != null) {
                predicates.add(cb.equal(root.<Integer>get("editShow"), tableConifgDTO.getEditShow()));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        List<TableConfig> tableConfigs = tableConfigDao.findAll(specification);
        List<TableConfigDTO> configDTOS = tableConfigMapper.from(tableConfigs);
        return configDTOS;
    }

    @Override
    public List<Map> queryConditionForm(TableConfigDTO paramDTO) {
        List<Map> dataMap = null;
        if(null != paramDTO.getQueryShow()){
            dataMap = tableConfigDao.queryConditionFormQuery(paramDTO.getTypeCode(),paramDTO.getTypeLevel(),paramDTO.getQueryShow());
        } else if(null != paramDTO.getListShow()){
            dataMap = tableConfigDao.queryConditionFormList(paramDTO.getTypeCode(),paramDTO.getTypeLevel(),paramDTO.getListShow());
        }
        return dataMap;
    }

    @Override
    public List<Map> queryDocNumberData(TableConfigParamDTO tableConfigParamDTO) {
        List<Map> list = tableConfigDao.queryDocNumberData(tableConfigParamDTO.getTypeCode(),tableConfigParamDTO.getTypeLevel());
        return list;
    }
}
