package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.ArchiveTypeDao;
import com.ebs.platform.business.domain.professionentity.ArchiveType;
import com.ebs.platform.business.dto.archive.ArchiveTypeDTO;
import com.ebs.platform.business.dto.archive.ArchiveTypeRequestParamDTO;
import com.ebs.platform.business.mapper.ArchiveTypeMapper;
import com.ebs.platform.business.mapper.ArchiveTypeNodeMapper;
import com.ebs.platform.business.service.ArchiveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by liujie on 2019/11/4.
 */
@Service
public class ArchiveTypeServiceImpl implements ArchiveTypeService {

    @Autowired
    private ArchiveTypeDao archiveTypeDao;
    @Autowired
    private ArchiveTypeMapper archiveTypeMapper;
    @Autowired
    private ArchiveTypeNodeMapper archiveTypeNodeMapper;

    @Override
    @Transactional
    public Integer saveArchiveType(ArchiveTypeDTO archiveType) {
        ArchiveType archiveType1 = archiveTypeMapper.to(archiveType);
        //ArchiveType save = archiveTypeDao.save(archiveType1);
        ArchiveType save = archiveTypeDao.dynamicSave(archiveType.getId(),archiveType1);
        return save.getId();
    }

    @Override
    @Transactional
    public Integer deleteArchiveType(Integer id) {
        ArchiveType archiveType = archiveTypeDao.getOne(id);
        archiveType.setDeleted(true);
        ArchiveType save = archiveTypeDao.save(archiveType);
        return save.getId();
    }

    @Override
    public ArchiveTypeDTO queryById(Integer id) {
        ArchiveType one = archiveTypeDao.getOne(id);
        if(one!=null){
            return archiveTypeMapper.from(one);
        }
        return null;
    }

    @Override
    public List<ArchiveTypeDTO> queryAll(ArchiveTypeRequestParamDTO requestDTO) {
        List<ArchiveType> archiveTypes = null;
        if(requestDTO!=null && requestDTO.getTypeLayer()!=null){
            archiveTypes = archiveTypeDao.queryAllByDeletedAndTypeLayerOrTypeLayerIsNull(false,requestDTO.getTypeLayer());
        }else {
            archiveTypes = archiveTypeDao.queryAllByDeleted(false);
        }
        List<ArchiveTypeDTO> rootList = new ArrayList<>();
        if(archiveTypes.size()>0){
            List<Object> sonList = new ArrayList<>();
            List<ArchiveTypeDTO> dtos = archiveTypeMapper.from(archiveTypes);
            for(ArchiveTypeDTO dto : dtos){
                if(dto.getParentCode()==null){
                    rootList.add(dto);
                }else {
                    sonList.add(dto);
                }
            }
            if(rootList.size()>0){
                for(int i =0; i < rootList.size(); i++){
                    if(sonList.size()>0){
                        List<Object> children = findChildren(rootList.get(i).getId(), sonList);
                        if(children.size()>0){
                            rootList.get(i).setChildren(children);
                        }
                    }
                }
            }
        }
        return rootList;
    }

    /**
     * 查询所有父节点
     * @return
     */
    @Override
    public List<Map> queryAllParent() {
        List<Map> dtos = archiveTypeDao.queryAllByDeletedAndTypeLayerIsNull(false);
        List<Map> rootList = new ArrayList<>();
        List<Map> resultList = new ArrayList<>();
        if(dtos.size()>0){
            List<Object> sonList = new ArrayList<>();
            //List<ArchiveTypeParentDTO> dtos = archiveTypeNodeMapper.from(archiveTypes);
            for(Map dto : dtos){
                if(dto.get("parentCode")==null){
                    rootList.add(dto);
                }else {
                    sonList.add(dto);
                }
            }
            if(rootList.size()>0){
                for(int i =0; i < rootList.size(); i++){
                    Map map = new HashMap();
                    Map rootMap = rootList.get(i);
                    map.put("id",rootMap.get("id"));
                    map.put("typeName",rootMap.get("typeName"));
                    map.put("parentCode",rootMap.get("parentCode"));
                    map.put("label",rootMap.get("label"));
                    map.put("parentName",rootMap.get("parentName"));
                    if(sonList.size()>0){
                        List<Object> children = findChildren((Integer) rootMap.get("id"), sonList);
                        if(children.size()>0){
                            map.put("children",children);
                        }
                    }
                    resultList.add(map);
                }
            }
        }
        return resultList;
    }

    @Override
    public List<Map> queryAllChildType() {
        List<Map> list = archiveTypeDao.queryAllByDeletedAndTypeLayerIsNotNull(false);
        List<Map> resultList = new ArrayList<>();
        if(list.size()>0){
            for(int i = 0;i < list.size(); i++){
                Map map = new HashMap();
                Map rootMap = list.get(i);
                Integer typeLayer = (Integer) rootMap.get("typeLayer");
                map.put("id",rootMap.get("id"));
                map.put("parentCode",rootMap.get("parentCode"));
                map.put("label",rootMap.get("label"));
                map.put("parentName",rootMap.get("parentName"));
                map.put("typeLayer",typeLayer);
                if(typeLayer==1){
                    map.put("typeLevel",3);
                    map.put("title",rootMap.get("title")+"(文件级)");
                }else if(typeLayer == 2){
                    HashMap hashMap = new HashMap();
                    hashMap.putAll(map);
                    hashMap.put("typeLevel",3);
                    hashMap.put("title",rootMap.get("title")+"(文件级)");
                    List<Map> children = new ArrayList<>();
                    children.add(hashMap);
                    map.put("children",children);
                    map.put("typeLevel",2);
                    map.put("title",rootMap.get("title")+"(案卷级)");
                } else {
                   HashMap hashMap = new HashMap();
                   hashMap.putAll(map);
                   hashMap.put("typeLevel",2);
                   hashMap.put("title",rootMap.get("title")+"(案卷级)");
                   HashMap fileMap = new HashMap();
                   fileMap.putAll(map);
                   fileMap.put("typeLevel",3);
                   fileMap.put("title",rootMap.get("title")+"(文件级)");
                   List<Map> children = new ArrayList<>();
                   children.add(fileMap);
                   hashMap.put("children",children);
                   map.put("typeLevel",1);
                   map.put("title",rootMap.get("title")+"(项目级)");
                    List<Map> children2 = new ArrayList<>();
                    children2.add(hashMap);
                   map.put("children",children2);
                }
                resultList.add(map);
            }
        }
        return resultList;
    }

    public List<Object> findChildren(Integer parentCode,List<Object> children){
        List<Object> childNodes = getChildNodes(parentCode, children);
        List<Object> allChildren = new ArrayList<>();
        for(Object dto : childNodes){
            if(dto instanceof ArchiveTypeDTO){
                ArchiveTypeDTO archiveTypeDTO = (ArchiveTypeDTO) dto;
                List<Object> children1 = findChildren(archiveTypeDTO.getId(), children);
                archiveTypeDTO.setChildren(children1);
                if(archiveTypeDTO.getParentCode()!=null && archiveTypeDTO.getParentCode().equals(parentCode)){
                    allChildren.add(dto);
                }
            }else {
                Map parentDTO = (Map) dto;
                List<Object> children1 = findChildren((Integer) parentDTO.get("id"), children);
                Map newMap = new HashMap();
                newMap.put("id",parentDTO.get("id"));
                newMap.put("typeName",parentDTO.get("typeName"));
                newMap.put("parentCode",parentDTO.get("parentCode"));
                newMap.put("label",parentDTO.get("label"));
                newMap.put("parentName",parentDTO.get("parentName"));
                if(children1!=null && children1.size()>0){
                    newMap.put("children",children1);
                }
                if(parentDTO.get("parentCode")!=null && parentDTO.get("parentCode").equals(parentCode)){
                    allChildren.add(newMap);
                }
            }
        }
        return allChildren;
    }

    /**
     * 获取当前节点的所有子节点
     * @param parentId
     * @param nodes
     * @return
     */
    public List<Object> getChildNodes(Integer parentId, List<Object> nodes){
        List<Object> list = new ArrayList<>();
        for (Object key : nodes ) {
            ArchiveTypeDTO archiveTypeDTO = null;
            if(key instanceof ArchiveTypeDTO){
                archiveTypeDTO = (ArchiveTypeDTO) key;
                if(archiveTypeDTO.getParentCode()!=null && archiveTypeDTO.getParentCode().equals(parentId)){
                    list.add(key);
                }
            }else {
                Map map = (Map) key;
                if(map.get("parentCode")!=null && map.get("parentCode").equals(parentId)){
                    Map newMap = new HashMap();
                    newMap.put("id",map.get("id"));
                    newMap.put("typeName",map.get("typeName"));
                    newMap.put("parentCode",map.get("parentCode"));
                    newMap.put("label",map.get("label"));
                    newMap.put("parentName",map.get("parentName"));
                    if(map.get("children")!=null){
                        newMap.put("children",map.get("children"));
                    }
                    list.add(newMap);
                }
            }
        }
        return list;
    }


}
