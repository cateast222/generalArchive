package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Company;
import com.ebs.platform.business.dto.company.CompanyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CompanyMapper {
    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "companyName",target = "companyName"),
            @Mapping(source = "typeCode",target = "typeCode"),
            @Mapping(source = "companyCode",target = "companyCode"),
            @Mapping(source = "linkMan",target = "linkMan"),
            @Mapping(source = "linkPhone",target = "linkPhone"),
            @Mapping(source = "address",target = "address")
    })
    CompanyDTO from(Company company);

    Company to(CompanyDTO companyDTO);

    List<CompanyDTO> from(List<Company> Company);

    List<Company> to(List<CompanyDTO> Company);

}
