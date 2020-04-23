package com.ebs.platform.business.dao.professiondao;

import com.ebs.platform.business.domain.professionentity.Company;
import com.ebs.platform.core.conf.ExtJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.hateoas.alps.Ext;

public interface CompanyDao extends ExtJpaRepository<Company,Integer> {

    Company queryCompanyById(Integer id);

}
