package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.IFondsDao;
import com.ebs.platform.business.domain.professionentity.Fonds;
import com.ebs.platform.business.dto.fonds.FondsDTO;
import com.ebs.platform.business.dto.fonds.FondsDTOByPage;
import com.ebs.platform.business.mapper.FondsMapper;
import com.ebs.platform.business.myutil.MyUtil;
import com.ebs.platform.business.service.IFondsService;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

@Service
public class FondsServiceImpl extends BaseServiceImpl implements IFondsService {

    @Autowired
    private IFondsDao iFondsDao;
    @Autowired
    private FondsMapper fondsMapper;
    private String code;

    @Override
    public void save(Fonds fonds) {
        MyUtil util = new MyUtil();
        util.save(fonds);
        iFondsDao.save(fonds);
    }

    @Override
    public Fonds findById(Integer id) {
        Fonds fonds = iFondsDao.queryByDeletedAndId(false,id);
        return fonds;
    }

    @Override
    public List<FondsDTOByPage> findByDeleted(boolean b) {
        List<Fonds> byDeleted = iFondsDao.findByDeleted(false);
        List<FondsDTOByPage> fondsDTOByPages = fondsMapper.from(byDeleted);
        return fondsDTOByPages;
    }

    @Override
    public QueryResponse<List<Fonds>> queryByFilter(QueryRequest<FondsDTO> param) {

        String searchText = param.getSearchText();
        FondsDTO condition = param.getCondition();
        Page<Fonds> fonds = null;
        String code = condition.getCode();
        String name = condition.getName();
        Pageable pageable = param.toPageable();
        if(!StringUtils.isBlank(searchText)){
            fonds =  iFondsDao.findByNameContainingOrCodeContainingaa(searchText,searchText,pageable);
        }else{

            fonds = iFondsDao.findAll((Specification<Fonds>) (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("deleted"),false));

                if(code != null){
                    predicates.add(cb.like(root.get("code"),"%"+code+"%"));
                }
                if(name != null){

                    predicates.add(cb.like(root.get("name"),"%"+name+"%"));
                }

                if(predicates.size() != 0){
                    Predicate[] p =new Predicate[predicates.size()];
                    return cb.and(predicates.toArray(p));
                }else {
                    return null;
                }

            },pageable);

        }
        if(fonds == null){
            return null;
        }

        List<Fonds> from = fonds.getContent();
        QueryResponse queryResponse = new QueryResponse(fonds, from);
        return queryResponse;
    }

    @Override
    public Fonds findByCode(Fonds fonds) {
        String code = fonds.getCode();
        Fonds fonds1 = iFondsDao.findByDeletedAndCode(false, code);
        Integer id = fonds1.getId();
        Integer id1 = fonds1.getId();
        if(id == id1){
            return null;
        }else{
            return fonds1;
        }
    }

    @Override
    public List<Map> getIdAndName() {
        List<Map> idAndName = iFondsDao.getIdAndName();
        return idAndName;
    }

}
