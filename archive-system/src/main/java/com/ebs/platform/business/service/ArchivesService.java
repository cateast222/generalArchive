package com.ebs.platform.business.service;


import com.ebs.platform.business.domain.professionentity.ArchiveFile;
import com.ebs.platform.business.dto.archive.*;
import com.ebs.platform.business.web.ArchivesController;
import com.ebs.platform.core.metadata.FunctionInfo;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by liujie on 2019/11/13.
 * 档案表
 */
public interface ArchivesService {

    /**
     * 保存档案
     * @param archivesDTO
     * @return
     */
    Integer saveArchives(ArchivesDTO archivesDTO);

    /**
     * 删除档案
     * @param id
     * @return
     */
    Integer deleteArchives(Integer id);


    /**
     * 查询档案表单字段
     * @return
     * @param archiveFormDTO
     */
    Map queryArchiveFormColumn(ArchiveFormDTO archiveFormDTO);

    QueryResponse<List<ArchivesDTO>> queryByFilter(QueryRequest<ArchivesDTO> req) throws IOException, ClassNotFoundException;

    FunctionInfo queryChildField(FunctionInfo functionInfo, ArchivesQueryParamDTO archivesQueryParamDTO, Class<ArchivesController> archivesControllerClass) throws IOException, ClassNotFoundException;

    void archvieFileUpload(Map map) throws Exception;

    void deleteArchiveFile(Map map);

    QueryResponse<List<OcrArchivesDTO>> queryOcrArchives(QueryRequest<OcrArchivesDTO> req);

    List<ArchivesShowDTO> queryByArchiveType(Integer archiveType);

    List<Map> queryArchives(ArchiveFormDTO archiveFormDTO);

    List<Map> queryRoomArchives(ArchiveRoomParamDTO archiveRoomParamDTO);

    /**
     * 查询过期档案
     * @param req
     * @return
     */
    QueryResponse<List<OcrArchivesDTO>> queryIdentifyArchives(QueryRequest<ArchivesPastDTO> req);
}
