package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.domain.AppDictDO;
import com.ebs.platform.core.dto.AppDictDTO;
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
public class AppDictMapperImpl implements AppDictMapper {

    @Override
    public AppDictDTO from(AppDictDO appDictDO) {
        if ( appDictDO == null ) {
            return null;
        }

        AppDictDTO appDictDTO = new AppDictDTO();

        String id = appDictDOAppId( appDictDO );
        if ( id != null ) {
            appDictDTO.setAppId( id );
        }
        appDictDTO.setId( appDictDO.getId() );
        appDictDTO.setDeleted( appDictDO.getDeleted() );
        appDictDTO.setRemark( appDictDO.getRemark() );
        appDictDTO.setScope( appDictDO.getScope() );
        appDictDTO.setName( appDictDO.getName() );
        appDictDTO.setCode( appDictDO.getCode() );

        return appDictDTO;
    }

    @Override
    public AppDictDO to(AppDictDTO appDictDTO) {
        if ( appDictDTO == null ) {
            return null;
        }

        AppDictDO appDictDO = new AppDictDO();

        appDictDO.setId( appDictDTO.getId() );
        appDictDO.setDeleted( appDictDTO.getDeleted() );
        appDictDO.setName( appDictDTO.getName() );
        appDictDO.setCode( appDictDTO.getCode() );
        appDictDO.setScope( appDictDTO.getScope() );
        appDictDO.setRemark( appDictDTO.getRemark() );

        return appDictDO;
    }

    @Override
    public List<AppDictDTO> from(List<AppDictDO> appDictDOS) {
        if ( appDictDOS == null ) {
            return null;
        }

        List<AppDictDTO> list = new ArrayList<AppDictDTO>( appDictDOS.size() );
        for ( AppDictDO appDictDO : appDictDOS ) {
            list.add( from( appDictDO ) );
        }

        return list;
    }

    @Override
    public List<AppDictDO> to(List<AppDictDTO> appDictDTOS) {
        if ( appDictDTOS == null ) {
            return null;
        }

        List<AppDictDO> list = new ArrayList<AppDictDO>( appDictDTOS.size() );
        for ( AppDictDTO appDictDTO : appDictDTOS ) {
            list.add( to( appDictDTO ) );
        }

        return list;
    }

    private String appDictDOAppId(AppDictDO appDictDO) {
        if ( appDictDO == null ) {
            return null;
        }
        AppDO app = appDictDO.getApp();
        if ( app == null ) {
            return null;
        }
        String id = app.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
