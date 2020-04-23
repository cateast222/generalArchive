package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppPowerExtDO;
import com.ebs.platform.business.dto.AppPowerExtDTO;
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
public class AppPowerExtMapperImpl implements AppPowerExtMapper {

    @Override
    public AppPowerExtDTO from(AppPowerExtDO appPowerExtDO) {
        if ( appPowerExtDO == null ) {
            return null;
        }

        AppPowerExtDTO appPowerExtDTO = new AppPowerExtDTO();

        appPowerExtDTO.setId( appPowerExtDO.getId() );
        appPowerExtDTO.setDeleted( appPowerExtDO.getDeleted() );
        appPowerExtDTO.setName( appPowerExtDO.getName() );
        appPowerExtDTO.setUrl( appPowerExtDO.getUrl() );

        return appPowerExtDTO;
    }

    @Override
    public AppPowerExtDO to(AppPowerExtDTO appPowerExtDTO) {
        if ( appPowerExtDTO == null ) {
            return null;
        }

        AppPowerExtDO appPowerExtDO = new AppPowerExtDO();

        appPowerExtDO.setId( appPowerExtDTO.getId() );
        appPowerExtDO.setDeleted( appPowerExtDTO.getDeleted() );
        appPowerExtDO.setName( appPowerExtDTO.getName() );
        appPowerExtDO.setUrl( appPowerExtDTO.getUrl() );

        return appPowerExtDO;
    }

    @Override
    public List<AppPowerExtDTO> from(List<AppPowerExtDO> appPowerExtDOS) {
        if ( appPowerExtDOS == null ) {
            return null;
        }

        List<AppPowerExtDTO> list = new ArrayList<AppPowerExtDTO>( appPowerExtDOS.size() );
        for ( AppPowerExtDO appPowerExtDO : appPowerExtDOS ) {
            list.add( from( appPowerExtDO ) );
        }

        return list;
    }

    @Override
    public List<AppPowerExtDO> to(List<AppPowerExtDTO> appPowerExtDTOS) {
        if ( appPowerExtDTOS == null ) {
            return null;
        }

        List<AppPowerExtDO> list = new ArrayList<AppPowerExtDO>( appPowerExtDTOS.size() );
        for ( AppPowerExtDTO appPowerExtDTO : appPowerExtDTOS ) {
            list.add( to( appPowerExtDTO ) );
        }

        return list;
    }
}
