package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Organization;
import com.ebs.platform.business.dto.organization.OrganizationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "orgName", target = "orgName"),
            @Mapping(source = "orgCode", target = "orgCode"),
            @Mapping(source = "englishName", target = "englishName"),
            @Mapping(source = "shortName", target = "shortName"),
            @Mapping(source = "orgPhone", target = "orgPhone"),
            @Mapping(source = "orgFax", target = "orgFax"),
            @Mapping(source = "orgOrder", target = "orgOrder"),
            @Mapping(source = "orgDesc", target = "orgDesc"),
            @Mapping(source = "deleted", target = "deleted")
    })
    OrganizationDTO from( Organization organization);
    Organization to(OrganizationDTO organizationDTO);
    List<OrganizationDTO> from(List<Organization> organizations);
    List<Organization> to(List<OrganizationDTO> organizationDTOS);

}
