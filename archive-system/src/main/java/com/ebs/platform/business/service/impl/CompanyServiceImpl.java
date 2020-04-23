package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.CompanyDao;
import com.ebs.platform.business.domain.professionentity.Company;
import com.ebs.platform.business.dto.company.CompanyDTO;
import com.ebs.platform.business.mapper.CompanyMapper;
import com.ebs.platform.business.myutil.MyUtil;
import com.ebs.platform.business.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.ebs.platform.core.util.PackageUtil.getUserContext;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyDao companyDao;
    @Autowired
    CompanyMapper companyMapper;
    @Override
    public Company queryById(Integer id) {
        return companyDao.queryCompanyById(id);
    }

    public void save(CompanyDTO companyDTO){
        companyDao.dynamicSaveDTO(companyDTO.getId(),companyDTO);
    }

    public List<CompanyDTO> queryAll(){
        List<Company> all = companyDao.findAll();
        List<CompanyDTO> companyDTOS = companyMapper.from(all);
        return companyDTOS ;
    }

}
