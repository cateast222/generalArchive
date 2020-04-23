package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.IOrganizationDao;
import com.ebs.platform.business.domain.professionentity.Organization;
import com.ebs.platform.business.dto.organization.OrganizationDTO;
import com.ebs.platform.business.dto.organization.OrganizationRequestDTO;
import com.ebs.platform.business.mapper.OrganizationMapper;
import com.ebs.platform.business.myutil.MyUtil;
import com.ebs.platform.business.service.IOrganizationService;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private IOrganizationDao orgDao;
    @Autowired
    OrganizationMapper orgMapper;
    @Override
    public QueryResponse<List<OrganizationDTO>> findbyFilter(QueryRequest<OrganizationRequestDTO> request) {
        String searchText = request.getSearchText();
        OrganizationRequestDTO condition =  request.getCondition();
        Page<Organization> byFilter = null;
        Pageable pageable = request.toPageable();
        if(!StringUtils.isEmpty(searchText)){
            byFilter = orgDao.findByFilter(searchText,pageable);
        }else {
            byFilter = orgDao.findAll((Specification<Organization>) (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                String orgName = condition.getOrgName();
                String orgCode = condition.getOrgCode();
                String englishName = condition.getEnglishName();
                String shortName = condition.getShortName();
                predicates.add(cb.equal(root.get("deleted"),false));

                if(!StringUtils.isEmpty(orgName)){
                    predicates.add(cb.like(root.get("orgName"),"%"+ orgName+"%" ));
                }
                if(!StringUtils.isEmpty(orgCode)){
                    predicates.add(cb.like(root.get("orgCode"),"%"+ orgCode+"%" ));
                }
                if(!StringUtils.isEmpty(englishName)){
                    predicates.add(cb.like(root.get("englishName"),"%"+ englishName+"%" ));
                }
                if(!StringUtils.isEmpty(shortName)){
                    predicates.add(cb.like(root.get("shortName"),"%"+ shortName+"%" ));
                }
                Predicate [] pred = new  Predicate[predicates.size()];
                return cb.and(predicates.toArray(pred));
            }, pageable);
        }

        List<Organization> content = byFilter.getContent();
        List<OrganizationDTO> from = orgMapper.from(content);
        QueryResponse<List<OrganizationDTO>> response = new QueryResponse(byFilter,from);
        return response;
    }

    @Override
    public void save(OrganizationDTO organizationDTO) {
        Organization organization = orgMapper.to(organizationDTO);
        Integer id = organizationDTO.getId();
        if(id !=null){
            Organization one = orgDao.getOne(id);
            organization.setCreateTime(one.getCreateTime());
            organization.setOperator(one.getOperator());
        }
        MyUtil util = new MyUtil();
        util.save(organization);
        orgDao.save(organization);
    }

    @Override
    public List<Map> findIdAndName() {
        return orgDao.queryIdAndName();
    }

}
