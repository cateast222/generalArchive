package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.dto.AppDTO;
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
public class AppMapperImpl implements AppMapper {

    @Override
    public AppDTO from(AppDO appDO) {
        if ( appDO == null ) {
            return null;
        }

        AppDTO appDTO = new AppDTO();

        appDTO.setId( appDO.getId() );
        appDTO.setName( appDO.getName() );

        return appDTO;
    }

    @Override
    public AppDO to(AppDTO appDTO) {
        if ( appDTO == null ) {
            return null;
        }

        AppDO appDO = new AppDO();

        appDO.setId( appDTO.getId() );
        appDO.setName( appDTO.getName() );

        return appDO;
    }

    @Override
    public List<AppDTO> from(List<AppDO> appDOS) {
        if ( appDOS == null ) {
            return null;
        }

        List<AppDTO> list = new ArrayList<AppDTO>( appDOS.size() );
        for ( AppDO appDO : appDOS ) {
            list.add( from( appDO ) );
        }

        return list;
    }

    @Override
    public List<AppDO> to(List<AppDTO> appDTOS) {
        if ( appDTOS == null ) {
            return null;
        }

        List<AppDO> list = new ArrayList<AppDO>( appDTOS.size() );
        for ( AppDTO appDTO : appDTOS ) {
            list.add( to( appDTO ) );
        }

        return list;
    }
}
