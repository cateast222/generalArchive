package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.archive.UnitDTO;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;

import java.util.List;

/**
 * @author lwy
 * @Date 2019-05-29 10:44
 */
public interface IUnitService {

    String add(UnitDTO unitDTO);

    void update(UnitDTO unitDTO);

    void remove(String id);

    UnitDTO queryById(String id);

    List<UnitDTO> findAll(String className);

    QueryResponse<List<UnitDTO>> queryByFilter(QueryRequest<UnitDTO> req);
}
