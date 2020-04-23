package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.IUnitDao;
import com.ebs.platform.business.domain.UnitDO;
import com.ebs.platform.business.dto.archive.UnitDTO;
import com.ebs.platform.business.mapper.UnitMapper;
import com.ebs.platform.business.service.IUnitService;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import com.ebs.platform.core.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 单位管理
 * @author lwy
 * @Date 2019-05-29 10:45
 */
@Service
public class UnitServiceImpl extends BaseService implements IUnitService{

    @Autowired
    private IUnitDao iUnitDao;

    @Autowired
    private UnitMapper unitMapper;

    @Override
    public String add(UnitDTO unitDTO) {
        UnitDO unitDO = unitMapper.DTOtoDO(unitDTO);
        iUnitDao.save(unitDO);
        return unitDO.getId();
    }

    @Override
    public void update(UnitDTO unitDTO) {
        this.add(unitDTO);
    }

    @Override
    public void remove(String id) {
        UnitDO unitDO = iUnitDao.findByIdAndDeleted(id,false);
        unitDO.setDeleted(true);
        iUnitDao.save(unitDO);
    }

    @Override
    public UnitDTO queryById(String id) {
        UnitDO unitDO = iUnitDao.findByIdAndDeleted(id,false);
        return unitMapper.DOtoDTO(unitDO);
    }

    @Override
    public List<UnitDTO> findAll(String className) {
        List<UnitDO> unitDOS;
        if (!StringUtils.isEmpty(className)){//UnitClassNameOr
            unitDOS = iUnitDao.findByUnitClassNameOrDictValueIdAndDeleted(className,className,false);
        }else {
            unitDOS = iUnitDao.findByDeleted(false);
        }

        return unitMapper.DOtoDTO(unitDOS);
    }

    @Override
    public QueryResponse<List<UnitDTO>> queryByFilter(QueryRequest<UnitDTO> req) {
        return null;
    }
}
