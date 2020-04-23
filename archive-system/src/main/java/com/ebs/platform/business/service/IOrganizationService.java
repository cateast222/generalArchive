package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.organization.OrganizationDTO;
import com.ebs.platform.business.dto.organization.OrganizationRequestDTO;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;

import java.util.List;
import java.util.Map;

public interface IOrganizationService {
    QueryResponse<List<OrganizationDTO>> findbyFilter(QueryRequest<OrganizationRequestDTO> request);
    void save(OrganizationDTO organizationDTO);
    List<Map> findIdAndName();
}
