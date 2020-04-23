package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppRoleDO;
import com.ebs.platform.business.domain.TenantDO;
import com.ebs.platform.business.dto.AppRoleDTO;
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
public class AppRoleMapperImpl implements AppRoleMapper {

    @Override
    public AppRoleDTO from(AppRoleDO appRoleDO) {
        if ( appRoleDO == null ) {
            return null;
        }

        AppRoleDTO appRoleDTO = new AppRoleDTO();

        String id = appRoleDOTenantId( appRoleDO );
        if ( id != null ) {
            appRoleDTO.setTenantId( id );
        }
        appRoleDTO.setId( appRoleDO.getId() );
        appRoleDTO.setDeleted( appRoleDO.getDeleted() );
        appRoleDTO.setName( appRoleDO.getName() );
        appRoleDTO.setCode( appRoleDO.getCode() );

        return appRoleDTO;
    }

    @Override
    public AppRoleDO to(AppRoleDTO appRoleDTO) {
        if ( appRoleDTO == null ) {
            return null;
        }

        AppRoleDO appRoleDO = new AppRoleDO();

        appRoleDO.setId( appRoleDTO.getId() );
        appRoleDO.setDeleted( appRoleDTO.getDeleted() );
        appRoleDO.setName( appRoleDTO.getName() );
        appRoleDO.setCode( appRoleDTO.getCode() );

        return appRoleDO;
    }

    @Override
    public List<AppRoleDTO> from(List<AppRoleDO> appRoleDOS) {
        if ( appRoleDOS == null ) {
            return null;
        }

        List<AppRoleDTO> list = new ArrayList<AppRoleDTO>( appRoleDOS.size() );
        for ( AppRoleDO appRoleDO : appRoleDOS ) {
            list.add( from( appRoleDO ) );
        }

        return list;
    }

    @Override
    public List<AppRoleDO> to(List<AppRoleDTO> appRoleDTOS) {
        if ( appRoleDTOS == null ) {
            return null;
        }

        List<AppRoleDO> list = new ArrayList<AppRoleDO>( appRoleDTOS.size() );
        for ( AppRoleDTO appRoleDTO : appRoleDTOS ) {
            list.add( to( appRoleDTO ) );
        }

        return list;
    }

    private String appRoleDOTenantId(AppRoleDO appRoleDO) {
        if ( appRoleDO == null ) {
            return null;
        }
        TenantDO tenant = appRoleDO.getTenant();
        if ( tenant == null ) {
            return null;
        }
        String id = tenant.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
