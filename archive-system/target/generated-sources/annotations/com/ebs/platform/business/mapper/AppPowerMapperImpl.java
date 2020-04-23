package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppPowerDO;
import com.ebs.platform.business.dto.AppPowerDTO;
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
public class AppPowerMapperImpl implements AppPowerMapper {

    @Override
    public AppPowerDTO from(AppPowerDO appPowerDO) {
        if ( appPowerDO == null ) {
            return null;
        }

        AppPowerDTO appPowerDTO = new AppPowerDTO();

        String id = appPowerDOParentId( appPowerDO );
        if ( id != null ) {
            appPowerDTO.setParentId( id );
        }
        appPowerDTO.setId( appPowerDO.getId() );
        appPowerDTO.setName( appPowerDO.getName() );
        appPowerDTO.setCode( appPowerDO.getCode() );
        appPowerDTO.setUrl( appPowerDO.getUrl() );
        appPowerDTO.setRemark( appPowerDO.getRemark() );
        appPowerDTO.setPowerType( appPowerDO.getPowerType() );
        appPowerDTO.setSort( appPowerDO.getSort() );

        return appPowerDTO;
    }

    @Override
    public AppPowerDO to(AppPowerDTO appPowerDTO) {
        if ( appPowerDTO == null ) {
            return null;
        }

        AppPowerDO appPowerDO = new AppPowerDO();

        appPowerDO.setId( appPowerDTO.getId() );
        appPowerDO.setName( appPowerDTO.getName() );
        appPowerDO.setCode( appPowerDTO.getCode() );
        appPowerDO.setPowerType( appPowerDTO.getPowerType() );
        appPowerDO.setUrl( appPowerDTO.getUrl() );
        appPowerDO.setSort( appPowerDTO.getSort() );
        appPowerDO.setRemark( appPowerDTO.getRemark() );

        return appPowerDO;
    }

    @Override
    public List<AppPowerDTO> from(List<AppPowerDO> appPowerDOS) {
        if ( appPowerDOS == null ) {
            return null;
        }

        List<AppPowerDTO> list = new ArrayList<AppPowerDTO>( appPowerDOS.size() );
        for ( AppPowerDO appPowerDO : appPowerDOS ) {
            list.add( from( appPowerDO ) );
        }

        return list;
    }

    @Override
    public List<AppPowerDO> to(List<AppPowerDTO> appPowerDTOS) {
        if ( appPowerDTOS == null ) {
            return null;
        }

        List<AppPowerDO> list = new ArrayList<AppPowerDO>( appPowerDTOS.size() );
        for ( AppPowerDTO appPowerDTO : appPowerDTOS ) {
            list.add( to( appPowerDTO ) );
        }

        return list;
    }

    private String appPowerDOParentId(AppPowerDO appPowerDO) {
        if ( appPowerDO == null ) {
            return null;
        }
        AppPowerDO parent = appPowerDO.getParent();
        if ( parent == null ) {
            return null;
        }
        String id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
