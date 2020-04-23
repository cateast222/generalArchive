package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Organization;
import com.ebs.platform.business.dto.organization.OrganizationDTO;
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
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public OrganizationDTO from(Organization organization) {
        if ( organization == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setOrgOrder( organization.getOrgOrder() );
        organizationDTO.setEnglishName( organization.getEnglishName() );
        organizationDTO.setOrgName( organization.getOrgName() );
        if ( organization.getDeleted() != null ) {
            organizationDTO.setDeleted( organization.getDeleted() );
        }
        organizationDTO.setOrgCode( organization.getOrgCode() );
        organizationDTO.setOrgFax( organization.getOrgFax() );
        organizationDTO.setId( organization.getId() );
        organizationDTO.setShortName( organization.getShortName() );
        organizationDTO.setOrgDesc( organization.getOrgDesc() );
        organizationDTO.setOrgPhone( organization.getOrgPhone() );

        return organizationDTO;
    }

    @Override
    public Organization to(OrganizationDTO organizationDTO) {
        if ( organizationDTO == null ) {
            return null;
        }

        Organization organization = new Organization();

        organization.setId( organizationDTO.getId() );
        organization.setDeleted( organizationDTO.isDeleted() );
        organization.setOrgName( organizationDTO.getOrgName() );
        organization.setOrgCode( organizationDTO.getOrgCode() );
        organization.setEnglishName( organizationDTO.getEnglishName() );
        organization.setShortName( organizationDTO.getShortName() );
        organization.setOrgPhone( organizationDTO.getOrgPhone() );
        organization.setOrgFax( organizationDTO.getOrgFax() );
        organization.setOrgOrder( organizationDTO.getOrgOrder() );
        organization.setOrgDesc( organizationDTO.getOrgDesc() );

        return organization;
    }

    @Override
    public List<OrganizationDTO> from(List<Organization> organizations) {
        if ( organizations == null ) {
            return null;
        }

        List<OrganizationDTO> list = new ArrayList<OrganizationDTO>( organizations.size() );
        for ( Organization organization : organizations ) {
            list.add( from( organization ) );
        }

        return list;
    }

    @Override
    public List<Organization> to(List<OrganizationDTO> organizationDTOS) {
        if ( organizationDTOS == null ) {
            return null;
        }

        List<Organization> list = new ArrayList<Organization>( organizationDTOS.size() );
        for ( OrganizationDTO organizationDTO : organizationDTOS ) {
            list.add( to( organizationDTO ) );
        }

        return list;
    }
}
