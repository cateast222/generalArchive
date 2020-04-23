package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.*;
import com.ebs.platform.business.dto.personnel.PersonnelByEditRequest;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;

import java.util.List;
import java.util.Map;

/**
 * 企业服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:34
 */
public interface IEntService {
    /**
     * 保存一个企业
     * @param entDTO
     */
    void saveEnt(EntDTO entDTO);
    
    /**
     * 保存一个企业
     * @param entDTO
     */
    String saveEntForPartner(EntDTO entDTO);

    /**
     * 删除一个企业
     * @param id
     */
    void deleteEnt(String id);

    /**
     * 列出所有企业
     * @return
     */
    QueryResponse<List<EntDTO>> listEnt(QueryRequest req);

    /**
     * 新建部门
     * @param req
     */
    void saveDept(EntDeptByEditRequest req);

    /**
     * 删除部门
     * @param id
     */
    void deleteEntDept(String id);

    /**
     * 列出企业下所有部门
     * @param entId
     * @return
     */
    List<EntDeptDTO> listEntDept(String entId);


    EntDeptDTO listEntDeptById(String deptId);

    EntDTO listEntById(String entId);


    /**
     * 删除人员
     * @param id
     */
    void deletePersonnel(String id);

    /**
     * 列出企业下所有人员
     * @param req
     * @return
     */
    QueryResponse<List<PersonnelDTO>> listPersonnelBySearch(QueryRequest req);

    /**
     * 列出部门下所有人员
     * @param req
     * @return
     */
    QueryResponse<List<PersonnelDTO>> listPersonnelByDeptId(QueryRequest<String> req);

    List<Map> listAccountByDeptId(String deptId);

    void savePersonnel(PersonnelByEditRequest req);

}
