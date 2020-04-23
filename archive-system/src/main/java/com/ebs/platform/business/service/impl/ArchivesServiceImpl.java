package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.IAppDictValueDao;
import com.ebs.platform.business.dao.IArchiveRoomDao;
import com.ebs.platform.business.dao.professiondao.ArchivesDao;
import com.ebs.platform.business.domain.AppDictValueDO;
import com.ebs.platform.business.domain.professionentity.ArchiveFile;
import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.*;
import com.ebs.platform.business.mapper.ArchivesMapper;
import com.ebs.platform.business.mapper.ArchivesShowMapper;
import com.ebs.platform.business.mapper.OcrArchivesMapper;
import com.ebs.platform.business.service.ArchiveFileService;
import com.ebs.platform.business.service.ArchivesService;
import com.ebs.platform.business.service.IAppService;
import com.ebs.platform.business.service.TableConfigService;
import com.ebs.platform.business.web.ArchivesController;
import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.enums.DataTypeEnum;
import com.ebs.platform.core.enums.ResourceData;
import com.ebs.platform.core.metadata.FunctionInfo;
import com.ebs.platform.core.query.MetadataFieldDTO;
import com.ebs.platform.core.query.MetadataFunction;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import com.ebs.platform.core.util.FileUploadUtil;
import com.ebs.platform.core.util.ListConverterUtil;
import com.ebs.platform.core.util.PackageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Created by liujie on 2019/11/13.
 */
@Service
public class ArchivesServiceImpl implements ArchivesService {

    @Autowired
    private ArchivesDao archivesDao;

    @Autowired
    private ArchivesMapper archivesMapper;

    @Autowired
    private TableConfigService tableConfigService;

    @Autowired
    private IAppService appService;
    @Autowired
    private IAppDictValueDao appDictValueDao;
    @Autowired
    private IArchiveRoomDao archiveRoomDao;
    @Autowired
    private ArchiveFileService archiveFileService;
    @Autowired
    private OcrArchivesMapper ocrArchivesMapper;
    @Autowired
    private ArchivesShowMapper archivesShowMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer saveArchives(ArchivesDTO archivesDTO) {
        Archives archives = archivesMapper.to(archivesDTO);
        List<Integer> archiveCabinetIdList = archivesDTO.getArchiveCabinetIdList();
        if(archiveCabinetIdList !=null && archiveCabinetIdList.size()>0){
            archives.setArchiveCabinetId(archiveCabinetIdList.get(archiveCabinetIdList.size()-1));
        }
        Archives save = archivesDao.dynamicSave(archives.getId(),archives);
        return save.getId();
    }

    @Override
    public Integer deleteArchives(Integer id) {
        Archives one = archivesDao.getOne(id);
        one.setDeleted(true);
        Archives save = archivesDao.save(one);
        return save.getId();
    }

    @Override
    public Map queryArchiveFormColumn(ArchiveFormDTO archiveFormDTO) {
        Map<String,Object> dataMap = new HashMap<>();
        if(null != archiveFormDTO.getId()){
            List<Archives> list = archivesDao.queryAllById(archiveFormDTO.getId());
            Archives archives = list.get(0);
            ArchivesDTO from = archivesMapper.from(archives);
            Integer archiveCabinetId = archives.getArchiveCabinetId();
            if(archiveCabinetId!=null && archiveCabinetId!=0){
                List<Integer> byParent = archiveRoomDao.findByParent(archiveCabinetId.toString());
                from.setArchiveCabinetIdList(byParent);
            }
            dataMap.put("valueData", from);
        }

        //新增或编辑时的表单字段
        TableConfigDTO tableConfigDTO = new TableConfigDTO();
        tableConfigDTO.setTypeCode(Integer.parseInt(archiveFormDTO.getArchiveType()));
        tableConfigDTO.setTypeLevel(archiveFormDTO.getArchiveLevel());
        tableConfigDTO.setEditShow(1);
        List<TableConfigDTO> tableConfigDTOS = tableConfigService.queryFormByCondition(tableConfigDTO);
        List<TableConfigAddOrUpdateFormDTO> result = new ArrayList<>();
        int size = tableConfigDTOS.size();
        if(size >0){
            for(int i =0;i < size; i++){
                TableConfigDTO DTO1 = tableConfigDTOS.get(i);
                String columnDict = DTO1.getColumnDict();
                TableConfigAddOrUpdateFormDTO addOrUpdateFormDTO = new TableConfigAddOrUpdateFormDTO();
                addOrUpdateFormDTO.setName(DTO1.getColumnCode());
                addOrUpdateFormDTO.setDisplayName(DTO1.getColumnName());
                addOrUpdateFormDTO.setMinLength(DTO1.getMinLength());
                addOrUpdateFormDTO.setColumnLength(DTO1.getColumnLength());
                addOrUpdateFormDTO.setIsPrimary(DTO1.getIsPrimary());
                addOrUpdateFormDTO.setIsRequired(DTO1.getIsRequired());
                addOrUpdateFormDTO.setColumnComponent(DTO1.getColumnComponent());
                addOrUpdateFormDTO.setColumnLimit(DTO1.getColumnLimit());
                addOrUpdateFormDTO.setDefaultValue(DTO1.getDefaultValue());
                addOrUpdateFormDTO.setEditOrder(DTO1.getEditOrder());
                //字段类型
                String columnTypecode = DTO1.getColumnTypecode();
                if (StringUtils.isNotBlank(columnTypecode)) {
                    AppDictValueDO appDictValueOne = appDictValueDao.getOne(columnTypecode);
                    String value = appDictValueOne.getValue().toLowerCase();
                    switch (value) {
                        case "string":
                            addOrUpdateFormDTO.setDataType(DataTypeEnum.STRING);
                            break;
                        case "datetype":
                            addOrUpdateFormDTO.setDataType(DataTypeEnum.DATE);
                            break;
                        case "inttype":
                            addOrUpdateFormDTO.setDataType(DataTypeEnum.INT);
                            break;
                        case "decimaltype":
                            addOrUpdateFormDTO.setDataType(DataTypeEnum.NUMBER);
                            break;
                        default:
                            addOrUpdateFormDTO.setDataType(DataTypeEnum.NONE);
                            break;
                    }
                    //List<AppDictValueDTO> appDictValueDTOS = appService.listAppDictValue(columnDict);
                    //tableConfigDTO1.setDict(appDictValueDTOS);
                }
                if(StringUtils.isNotBlank(columnDict)) {
                    List<AppDictValueDTO> appDictValueDTOS = appService.listAppDictValue(columnDict);
                    List<ResourceData> resourceDatas = new ArrayList();
                    for (AppDictValueDTO value : appDictValueDTOS) {
                        resourceDatas.add(new ResourceData(columnDict, value.getId(), value.getName()));
                    }
                    addOrUpdateFormDTO.setSelectData(resourceDatas);
                }
                result.add(addOrUpdateFormDTO);
            }
        }
        dataMap.put("columnInfo",result);
        return dataMap;
    }

    @Override
    public QueryResponse<List<ArchivesDTO>> queryByFilter(QueryRequest<ArchivesDTO> req) throws IOException, ClassNotFoundException {
        String searchText = req.getSearchText();
        ArchivesDTO condition = req.getCondition();
        Page<Archives> page = null;

        if(!StringUtils.isBlank(searchText)){
            page =  archivesDao.findByArchiveTypeAndArchiveLevelAndArchiveStatusOrArchiveCodeLikeOrArchiveFondsLikeOrTitleLikeOrArchiveTimeLike(
                    req.getArchiveType().toString(),req.getArchiveLevel(),req.getArchiveStatus(),searchText,searchText,searchText,searchText,req.toPageable());
        }else if (condition!=null){
            Pageable pageable = req.toPageable();
            page = archivesDao.findAll((Specification<Archives>) (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("deleted"),false));
                predicates.add(cb.equal(root.get("archiveType"),req.getArchiveType()));
                predicates.add(cb.equal(root.get("archiveLevel"),req.getArchiveLevel()));
                if(StringUtils.isNotBlank(condition.getArchiveCode())){
                    predicates.add(cb.like(root.get("archiveCode"),"%"+condition.getArchiveCode()+"%"));
                }
                if(StringUtils.isNotBlank(condition.getArchiveFonds())){
                    predicates.add(cb.like(root.get("archiveFonds"),"%"+condition.getArchiveFonds()+"%"));
                }
                if(StringUtils.isNotBlank(condition.getTitle())){
                    predicates.add(cb.like(root.get("title"),"%"+condition.getTitle()+"%"));
                }
                if(StringUtils.isNotBlank(condition.getArchiveTime())){
                    predicates.add(cb.like(root.get("archiveTime"),"%"+condition.getArchiveTime()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            },pageable);
        }else {
            page =  archivesDao.findByArchiveTypeAndArchiveLevelAndArchiveStatus(
                    req.getArchiveType().toString(),req.getArchiveLevel(),req.getArchiveStatus(),req.toPageable());
        }
        List<Archives> content = page.getContent();
        List<ArchivesDTO> from = archivesMapper.from(content);
        return new QueryResponse(page,from);
    }

    @Override
    public FunctionInfo queryChildField(FunctionInfo functionInfo, ArchivesQueryParamDTO archivesQueryParamDTO, Class<ArchivesController> cls) throws IOException, ClassNotFoundException {
        List<MetadataFieldDTO> showList = getShowList(archivesQueryParamDTO, cls);
        //获取元数据中动态的查询条件字段
        /*TableConfigDTO tableConfigDTO = new TableConfigDTO();
        tableConfigDTO.setTypeCode(Integer.parseInt(archivesQueryParamDTO.getArchiveType()));
        tableConfigDTO.setTypeLevel(archivesQueryParamDTO.getArchiveLevel());
        tableConfigDTO.setQueryShow(1);
        List<Map> conditionForm = tableConfigService.queryConditionForm(tableConfigDTO);
        int size = conditionForm.size();
        if(size >0){
            for(int i = 0; i < size; i++){
                Map map = conditionForm.get(i);
                MetadataFieldDTO metadataFieldDTO = new MetadataFieldDTO();
                metadataFieldDTO.setKey(false);
                metadataFieldDTO.setColumn(true);
                metadataFieldDTO.setFilter(true);
                metadataFieldDTO.setFilterHidden(false);
                metadataFieldDTO.setName((String) map.get("name"));
                metadataFieldDTO.setDisplayName((String) map.get("displayName"));
                //字段类型 例如：String int
                String columnTypecode = (String) map.get("columnTypecode");
                if(StringUtils.isNotBlank(columnTypecode)){
                    AppDictValueDO one = appDictValueDao.getOne(columnTypecode);
                    String value = one.getValue().toLowerCase();
                    switch (value){
                        case "string":
                            metadataFieldDTO.setDataType(DataTypeEnum.STRING);
                            break;
                        case "datetype":
                            metadataFieldDTO.setDataType(DataTypeEnum.DATE);
                            break;
                        case "inttype":
                            metadataFieldDTO.setDataType(DataTypeEnum.INT);
                            break;
                        case "decimaltype":
                            metadataFieldDTO.setDataType(DataTypeEnum.NUMBER);
                            break;
                        default:
                            metadataFieldDTO.setDataType(DataTypeEnum.NONE);
                            break;
                    }
                    //关联字典
                    String dict = (String) map.get("columnDict");
                    if(StringUtils.isNotBlank(dict)){
                        metadataFieldDTO.setSelectData(ResourceData.fromDictionary(dict,PackageUtil.getDictValues(dict)));
                    }
                }
                metadata.add(metadataFieldDTO);
            }
        }
        List<MetadataFieldDTO> request = functionInfo.getRequest();
        for(MetadataFieldDTO metadataDto : request){
            if(metadataDto.getName().equals("condition")){
                metadataDto.setChildField(metadata);
                break;
            }
        }*/

        List<MetadataFieldDTO> response = functionInfo.getResponse();
        for(MetadataFieldDTO metadataDto : response){
            if(metadataDto.getName().equals("condition")){
                metadataDto.setChildField(showList);
                break;
            }
        }

        return functionInfo;
    }

    @Override
    @Transactional
    public void archvieFileUpload(Map map) throws Exception {
        MultipartFile[] files = (MultipartFile[]) map.get("files");
        for(int i =0;i<files.length;i++){
            Integer id = (Integer) map.get("id");
            String filePath = FileUploadUtil.uploadFile(files[i]);
            ArchiveFile archiveFile = new ArchiveFile();
            archiveFile.setFileUrl(filePath);
            archiveFile.setFileName(filePath.split("/")[1]);
            archiveFile.setCreateTime(new Date());
            archiveFile.setDeleted(false);
            archiveFile.setArchiveId(id);
            archiveFileService.saveArchiveFile(archiveFile);
        }
    }

    @Override
    @Transactional
    public void deleteArchiveFile(Map map) {
        Integer id = (Integer) map.get("id");
        ArchiveFile archiveFile = archiveFileService.queryById(id);
        Integer integer = archiveFileService.deleteArchiveFile(id);
        String fileUrl = archiveFile.getFileUrl();
        File file = new File(fileUrl);
        file.delete();
    }

    @Override
    public QueryResponse<List<OcrArchivesDTO>> queryOcrArchives(QueryRequest<OcrArchivesDTO> req) {
        /*String searchText = req.getSearchText();
        OcrArchivesDTO condition = req.getCondition();*/
        Page page = null;
        page =  archivesDao.findAllByDeletedAndContainsFile2(req);
        //List<OcrArchivesDTO> from = ocrArchivesMapper.from(page.getContent());
        return new QueryResponse(page,page.getContent());
    }

    @Override
    public List<ArchivesShowDTO> queryByArchiveType(Integer archiveType) {
        List<Archives> archives = archivesDao.queryAllByDeletedAndArchiveTypeAndArchiveStatusNot(false, archiveType.toString(), 2);
        List<ArchivesShowDTO> from = new ArrayList<>();
        List<ArchivesShowDTO> parent = new ArrayList<>();
        List<Object> children = new ArrayList<>();
        if(archives.size()>0){
            from = archivesShowMapper.from(archives);
            for(int i =0;i<from.size();i++){
                ArchivesShowDTO archive = from.get(i);
                if(archive.getParentId()!=null && archive.getParentId()!=0){
                    children.add(archive);
                }else{
                    parent.add(archive);
                }
            }
            int size = parent.size();
            if(size >0){
                for(int i = 0; i< size; i++){
                    ArchivesShowDTO archivesShowDTO = parent.get(i);
                    List<Object> children1 = findChildren(archivesShowDTO.getId(), children);
                    if(children1.size()>0){
                        archivesShowDTO.setChildren(children1);
                    }
                }
            }
        }
        return parent;
    }

    @Override
    public List<Map> queryArchives(ArchiveFormDTO archiveFormDTO) {
        List<Map> list = archivesDao.queryArchives(Integer.parseInt(archiveFormDTO.getArchiveType()));
        List<Object> parentList = new ArrayList<>();
        List<Object> childList = new ArrayList<>();
        List<Map> result = new ArrayList<>();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                Map map = list.get(i);
                Integer parentId = (Integer) map.get("parentId");
                if(parentId == null){
                    parentList.add(map);
                }else {
                   childList.add(map);
                }
            }
            if(parentList.size()>0){
                for(int i =0;i<parentList.size();i++){
                    HashMap newMap = new HashMap();
                    Map map = (Map) parentList.get(i);
                    List<Object> children1 = findChildren((Integer) map.get("id"), childList);
                    newMap.putAll(map);
                    if(children1.size()>0){
                        newMap.put("children",children1);
                    }
                    result.add(newMap);
                }
            }
        }
        return result;
    }

    @Override
    public List<Map> queryRoomArchives(ArchiveRoomParamDTO archiveRoomParamDTO) {
        List<Object[]> list = archivesDao.queryRoomArchives(archiveRoomParamDTO);
        List<Map> result = new ArrayList<>();
        if(list!=null && list.size()>0){
            for(int i =0;i<list.size();i++){
                Map map = new HashMap();
                Object[] objects = list.get(i);
                map.put("title",objects[0]);
                map.put("typeName",objects[1]);
                map.put("archiveCode",objects[2]);
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public QueryResponse<List<OcrArchivesDTO>> queryIdentifyArchives(QueryRequest<ArchivesPastDTO> req) {
        return null;
    }

    /**
     * 递归获取所有节点的子节点
     * @param parentCode
     * @param children
     * @return
     */
    public List<Object> findChildren(Integer parentCode,List<Object> children){
        List<Object> childNodes = getChildNodes(parentCode, children);
        List<Object> results = new ArrayList<>();
        for(int i =0;i<childNodes.size();i++){
            Object o = childNodes.get(i);
            if(o instanceof Map){
                Map map = (Map) o;
                HashMap newMap = new HashMap();
                List<Object> children1 = findChildren((Integer) map.get("id"), children);
                newMap.putAll(map);
                if(children1.size()>0){
                    newMap.put("children",children1);
                }
                results.add(newMap);
            }else {
                ArchivesShowDTO dto = (ArchivesShowDTO) o;
                List<Object> children1 = findChildren(dto.getId(), children);
                if(children1.size()>0){
                    dto.setChildren(children1);
                }
            }
        }
        if(childNodes.size()>0 && childNodes.get(0) instanceof Map){
            return results;
        }
        return childNodes;
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
            Object o = nodes.get(i);
            if(nodes.get(i) instanceof Map){
                Map map = (Map)o;
                if(map.get("parentId")!=null && map.get("parentId").equals(parentId)){
                    list.add(map);
                }
            }else {
                ArchivesShowDTO dto = (ArchivesShowDTO) o;
                if(dto.getParentId()!=null && dto.getParentId().equals(parentId)){
                    list.add(dto);
                }
            }
        }
        return list;
    }

    private List<MetadataFieldDTO> getShowList(ArchivesQueryParamDTO archivesQueryParamDTO, Class<ArchivesController> cls) throws IOException, ClassNotFoundException {
        List<MetadataFieldDTO> metadata = new ArrayList<>();
        List<MetadataFieldDTO> copyMetadata = new ArrayList<>();
        //获取dto中查询条件字段
        for(Method m : cls.getMethods()){
            MetadataFunction funMethod = m.getAnnotation(MetadataFunction.class);
            if(funMethod==null) {
                continue;
            }
            if(m.getParameterCount()>0 && m.getParameters()[0].getParameterizedType() !=null) {
                String sourceType = ((ParameterizedType) m.getParameters()[0].getParameterizedType()).getActualTypeArguments()[0].getTypeName();
                metadata = PackageUtil.getMetadata(m.getParameters()[0].getType(), ((ParameterizedType) m.getParameters()[0].getParameterizedType()).getActualTypeArguments()[0], sourceType, 1);
                if(metadata.size()>0){
                    copyMetadata = ListConverterUtil.deepCopy(metadata);
                }
            }
        }
        //获取动态显示列表的字段
        TableConfigDTO tableConfigDTO2 = new TableConfigDTO();
        tableConfigDTO2.setTypeCode(Integer.parseInt(archivesQueryParamDTO.getArchiveType()));
        tableConfigDTO2.setTypeLevel(archivesQueryParamDTO.getArchiveLevel());
        tableConfigDTO2.setListShow(1);
        List<Map> conditionFormList = tableConfigService.queryConditionForm(tableConfigDTO2);
        for(int i =0;i < conditionFormList.size(); i++){
            Map map = conditionFormList.get(i);
            MetadataFieldDTO metadataFieldDTO = new MetadataFieldDTO();
            metadataFieldDTO.setKey(false);
            metadataFieldDTO.setColumn(true);
            metadataFieldDTO.setFilter(true);
            metadataFieldDTO.setFilterHidden(false);
            metadataFieldDTO.setName((String) map.get("name"));
            metadataFieldDTO.setDisplayName((String) map.get("displayName"));
            copyMetadata.add(metadataFieldDTO);
        }
        return copyMetadata;
    }
}
