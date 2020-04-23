package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.EntityTypeDao;
import com.ebs.platform.business.domain.professionentity.EntityType;
import com.ebs.platform.business.dto.archive.EntityTypeDTO;
import com.ebs.platform.business.mapper.EntityTypeMapper;
import com.ebs.platform.business.mapper.EntityTypeNodeMapper;
import com.ebs.platform.business.service.EntityTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by liujie on 2019/11/12.
 */
@Service
public class EntityTypeServiceImpl implements EntityTypeService {

    @Autowired
    private EntityTypeDao entityTypeDao;
    @Autowired
    private EntityTypeMapper entityTypeMapper;
    @Autowired
    private EntityTypeNodeMapper entityTypeNodeMapper;

    @Override
    @Transactional
    public Integer saveEntityType(EntityTypeDTO entityType) {
        EntityType to = entityTypeMapper.to(entityType);
        EntityType save = entityTypeDao.dynamicSave(to.getId(),to);
        return save.getId();
    }

    @Override
    @Transactional
    public Integer deleteEntityType(Integer id) {
        EntityType one = entityTypeDao.getOne(id);
        one.setDeleted(true);
        EntityType save = entityTypeDao.save(one);
        return save.getId();
    }

    @Override
    public EntityTypeDTO queryById(Integer id) {
        EntityType one = entityTypeDao.getOne(id);
        EntityTypeDTO entityTypeDTO = entityTypeMapper.from(one);
        return entityTypeDTO;
    }

    @Override
    public List<Map> queryAll(Integer id) {
        List<Map> newParentList = new ArrayList<>();
        //List<EntityTypeDTO> entityTypeDTOS = new ArrayList<>();
        List<Map> entityTypes = entityTypeDao.queryAllByDeletedAndEntityFonds(false,id.toString());
        List<Map> parentList = new ArrayList<>();
        if(entityTypes.size()>0){
            //entityTypeDTOS = entityTypeMapper.from(entityTypes);
            int size = entityTypes.size();
            List<Object> children = new ArrayList<>();
            for(int i =0;i<size;i++){
                Map dto = entityTypes.get(i);
                if(StringUtils.isBlank((String)dto.get("parent_code"))){
                    parentList.add(dto);
                } else {
                    children.add(dto);
                }
            }
            int parentSize = parentList.size();
            if(parentSize >0){
                for(int i =0; i<parentSize; i++){
                    Map entityTypeDTO = parentList.get(i);
                    Map<String,Object> map = new HashMap();
                    map.put("entityCode",entityTypeDTO.get("entity_code"));
                    map.put("entityDesc",entityTypeDTO.get("entity_desc"));
                    map.put("entityFonds",entityTypeDTO.get("entity_fonds"));
                    map.put("entityName",entityTypeDTO.get("entity_name"));
                    map.put("entityOrder",entityTypeDTO.get("entity_order"));
                    map.put("id",entityTypeDTO.get("id"));
                    map.put("parentCode",entityTypeDTO.get("parent_code"));
                    if(children.size()>0){
                        List<Object> allChildren = findChildren((Integer) entityTypeDTO.get("id"), children);
                        if(allChildren.size()>0){
                            //entityTypeDTO.setChildren(allChildren);
                            map.put("children",allChildren);
                        }
                    }
                    newParentList.add(map);
                }
            }
        }
        return newParentList;
    }

    @Override
    public List<Map> queryEntityTypeSelectData(EntityTypeDTO entityTypeDTO) {
        //List<EntityTypeNodeDTO> list = null;
        List<Map> resultList = new ArrayList<>();
        List<Map> parentList = new ArrayList<>();
        List<Map> dataResult = new ArrayList<>();
        if(StringUtils.isNotBlank(entityTypeDTO.getEntityFonds())){
            if(entityTypeDTO.getId()!=null && entityTypeDTO.getId()!=0){
                resultList = entityTypeDao.queryEntityTypeSelectDataByEntityFondsAndIdNot(Integer.parseInt(entityTypeDTO.getEntityFonds()),entityTypeDTO.getId());
            }else {
                resultList = entityTypeDao.queryEntityTypeSelectDataByEntityFonds(Integer.parseInt(entityTypeDTO.getEntityFonds()));
            }
        }else {
            resultList = entityTypeDao.queryEntityTypeSelectData();
        }
        if(resultList.size()>0){
            //list = entityTypeNodeMapper.from(resultList);
            int size = resultList.size();
            List<Object> children = new ArrayList<>();
            if(size > 0){
                for(int i =0; i<size; i++){
                    Map dto = resultList.get(i);
                    if(StringUtils.isBlank((String)dto.get("parentId"))){
                        parentList.add(dto);
                    } else {
                        children.add(dto);
                    }
                }
                int parentSize = parentList.size();
                if(parentSize >0){
                    for(int i =0; i<parentSize; i++){
                        Map selectDTO = parentList.get(i);
                        Map map = new HashMap();
                        map.put("id",selectDTO.get("id"));
                        map.put("label",selectDTO.get("label"));
                        map.put("parentId",selectDTO.get("parentId"));
                        if(children.size()>0){
                            List<Object> allChildren = findChildren((Integer) selectDTO.get("id"), children);
                            if(allChildren.size()>0){
                                map.put("children",allChildren);
                            }
                        }
                        dataResult.add(map);
                    }
                }
            }
        }
        return dataResult;
    }

    /**
     * 递归获取所有节点的子节点
     * @param parentCode
     * @param children
     * @return
     */
    public List<Object> findChildren(Integer parentCode,List<Object> children){
        List<Object> childNodes = getChildNodes(parentCode, children);
        //List<Object> allChildren = new ArrayList<>();
        List<Object> mapChildren = new ArrayList<>();
        for(int i =0;i<childNodes.size();i++){
            Map dto = (Map) childNodes.get(i);
            if(!dto.containsKey("entity_fonds")){
                //EntityTypeNodeDTO entityTypeSelectDTO = (EntityTypeNodeDTO) dto;
                List<Object> children1 = findChildren((Integer) dto.get("id"), children);
                Map map = new HashMap();
                map.put("id",dto.get("id"));
                map.put("label",dto.get("label"));
                map.put("parentId",dto.get("parentId"));
                if(children1.size()>0){
                    map.put("children",children1);
                    //entityTypeSelectDTO.setChildren(children1);
                }
                mapChildren.add(map);
                //allChildren.add(entityTypeSelectDTO);
            }else {
                //EntityTypeDTO entityTypeDTO = (EntityTypeDTO) dto;
                List<Object> children1 = findChildren((Integer) dto.get("id"), children);
                Map map = new HashMap();
                map.put("entityName",dto.get("entity_name"));
                map.put("entityCode",dto.get("entity_code"));
                map.put("parentCode",dto.get("parent_code"));
                map.put("entityOrder",dto.get("entity_order"));
                map.put("entityDesc",dto.get("entity_desc"));
                map.put("entityFonds",dto.get("entity_fonds"));
                map.put("id",dto.get("id"));
                if(children1.size()>0){
                    map.put("children",children1);
                    //entityTypeDTO.setChildren(children1);
                }
                mapChildren.add(map);
                //allChildren.add(entityTypeDTO);
            }
        }
        return mapChildren;
    }

    /**
     * 获取当前节点的所有子节点
     * @param parentId
     * @param nodes
     * @return
     */
    public List<Object> getChildNodes(Integer parentId, List<Object> nodes){
        List<Object> list = new ArrayList<>();
        for (int i = 0;i<nodes.size();i++) {
            Map dto = (Map) nodes.get(i);
            if(!dto.containsKey("entity_fonds")){
                //EntityTypeNodeDTO dto = (EntityTypeNodeDTO) key;
                if(dto.get("parentId")!=null && dto.get("parentId").equals(parentId.toString())){
                    //list.add(key);
                    /*map.put("id",dto.getId());
                    map.put("label",dto.getLabel());
                    map.put("parentId",dto.getParentId());
                    if(dto.getChildren()!=null){
                        map.put("children",dto.getChildren());
                        //entityTypeSelectDTO.setChildren(children1);
                    }*/
                    list.add(dto);
                }
            }else {
                //EntityTypeDTO dto = (EntityTypeDTO) key;

                    if(dto.get("parent_code")!=null && dto.get("parent_code").equals(parentId.toString())){
                   /* map.put("entityName",dto.getEntityName());
                    map.put("entityCode",dto.getEntityCode());
                    map.put("parentCode",dto.getParentCode());
                    map.put("entityOrder",dto.getEntityOrder());
                    map.put("entityDesc",dto.getEntityDesc());
                    map.put("entityFonds",dto.getEntityFonds());
                    if(dto.getChildren()!=null){
                        map.put("children",dto.getChildren());
                    }*/
                    //list.add(key);
                    list.add(dto);
                }
            }
        }
        return list;
    }
}
