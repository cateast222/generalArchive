package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Company;
import com.ebs.platform.business.dto.company.CompanyDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-03T17:06:49+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDTO from(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setCompanyCode( company.getCompanyCode() );
        companyDTO.setLinkPhone( company.getLinkPhone() );
        companyDTO.setAddress( company.getAddress() );
        companyDTO.setCompanyName( company.getCompanyName() );
        companyDTO.setId( company.getId() );
        companyDTO.setLinkMan( company.getLinkMan() );
        companyDTO.setTypeCode( company.getTypeCode() );

        return companyDTO;
    }

    @Override
    public Company to(CompanyDTO companyDTO) {
        if ( companyDTO == null ) {
            return null;
        }

        Company company = new Company();

        company.setId( companyDTO.getId() );
        company.setCompanyName( companyDTO.getCompanyName() );
        company.setTypeCode( companyDTO.getTypeCode() );
        company.setCompanyCode( companyDTO.getCompanyCode() );
        company.setLinkMan( companyDTO.getLinkMan() );
        company.setLinkPhone( companyDTO.getLinkPhone() );
        company.setAddress( companyDTO.getAddress() );

        return company;
    }

    @Override
    public List<CompanyDTO> from(List<Company> Company) {
        if ( Company == null ) {
            return null;
        }

        List<CompanyDTO> list = new ArrayList<CompanyDTO>( Company.size() );
        for ( Company company : Company ) {
            list.add( from( company ) );
        }

        return list;
    }

    @Override
    public List<Company> to(List<CompanyDTO> Company) {
        if ( Company == null ) {
            return null;
        }

        List<Company> list = new ArrayList<Company>( Company.size() );
        for ( CompanyDTO companyDTO : Company ) {
            list.add( to( companyDTO ) );
        }

        return list;
    }
}
