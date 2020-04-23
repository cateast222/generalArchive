package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.domain.EntDO;
import com.ebs.platform.business.domain.TenantDO;
import com.ebs.platform.business.dto.TenantDTO;
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
public class TenantMapperImpl implements TenantMapper {

    @Override
    public TenantDTO from(TenantDO tenantDO) {
        if ( tenantDO == null ) {
            return null;
        }

        TenantDTO tenantDTO = new TenantDTO();

        String id = tenantDOAppId( tenantDO );
        if ( id != null ) {
            tenantDTO.setAppId( id );
        }
        String id1 = tenantDOEnterpriseId( tenantDO );
        if ( id1 != null ) {
            tenantDTO.setRentId( id1 );
        }
        tenantDTO.setId( tenantDO.getId() );
        tenantDTO.setDeleted( tenantDO.getDeleted() );
        tenantDTO.setStartDate( tenantDO.getStartDate() );
        tenantDTO.setEndDate( tenantDO.getEndDate() );
        tenantDTO.setTenantType( tenantDO.getTenantType() );

        return tenantDTO;
    }

    @Override
    public TenantDO to(TenantDTO tenantDTO) {
        if ( tenantDTO == null ) {
            return null;
        }

        TenantDO tenantDO = new TenantDO();

        tenantDO.setId( tenantDTO.getId() );
        tenantDO.setDeleted( tenantDTO.getDeleted() );
        tenantDO.setStartDate( tenantDTO.getStartDate() );
        tenantDO.setEndDate( tenantDTO.getEndDate() );
        tenantDO.setTenantType( tenantDTO.getTenantType() );

        return tenantDO;
    }

    @Override
    public List<TenantDTO> from(List<TenantDO> tenantDOS) {
        if ( tenantDOS == null ) {
            return null;
        }

        List<TenantDTO> list = new ArrayList<TenantDTO>( tenantDOS.size() );
        for ( TenantDO tenantDO : tenantDOS ) {
            list.add( from( tenantDO ) );
        }

        return list;
    }

    @Override
    public List<TenantDO> to(List<TenantDTO> tenantDTOS) {
        if ( tenantDTOS == null ) {
            return null;
        }

        List<TenantDO> list = new ArrayList<TenantDO>( tenantDTOS.size() );
        for ( TenantDTO tenantDTO : tenantDTOS ) {
            list.add( to( tenantDTO ) );
        }

        return list;
    }

    private String tenantDOAppId(TenantDO tenantDO) {
        if ( tenantDO == null ) {
            return null;
        }
        AppDO app = tenantDO.getApp();
        if ( app == null ) {
            return null;
        }
        String id = app.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String tenantDOEnterpriseId(TenantDO tenantDO) {
        if ( tenantDO == null ) {
            return null;
        }
        EntDO enterprise = tenantDO.getEnterprise();
        if ( enterprise == null ) {
            return null;
        }
        String id = enterprise.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
