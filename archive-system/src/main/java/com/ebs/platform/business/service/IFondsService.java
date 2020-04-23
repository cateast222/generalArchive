package com.ebs.platform.business.service;

import com.ebs.platform.business.domain.professionentity.Fonds;
import com.ebs.platform.business.dto.fonds.FondsDTO;
import com.ebs.platform.business.dto.fonds.FondsDTOByPage;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;

import java.util.List;
import java.util.Map;

public interface IFondsService {

    void save(Fonds fonds);

    Fonds findById(Integer id);
    List<FondsDTOByPage> findByDeleted(boolean b);
    QueryResponse<List<Fonds>> queryByFilter(QueryRequest<FondsDTO> param);

    Fonds findByCode(Fonds fonds);

    List<Map> getIdAndName();

}
