package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppDictDO;
import com.ebs.platform.business.domain.AppDictValueDO;
import com.ebs.platform.core.dto.AppDictValueDTO;
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
public class AppDictValueMapperImpl implements AppDictValueMapper {

    @Override
    public AppDictValueDTO from(AppDictValueDO appDictValueDO) {
        if ( appDictValueDO == null ) {
            return null;
        }

        AppDictValueDTO appDictValueDTO = new AppDictValueDTO();

        appDictValueDTO.setId( appDictValueDO.getId() );
        String id = appDictValueDODictId( appDictValueDO );
        if ( id != null ) {
            appDictValueDTO.setDictId( id );
        }
        String id1 = appDictValueDOParentId( appDictValueDO );
        if ( id1 != null ) {
            appDictValueDTO.setParentId( id1 );
        }
        appDictValueDTO.setDeleted( appDictValueDO.getDeleted() );
        appDictValueDTO.setName( appDictValueDO.getName() );
        appDictValueDTO.setValue( appDictValueDO.getValue() );
        appDictValueDTO.setRemark( appDictValueDO.getRemark() );
        if ( appDictValueDO.getSort() != null ) {
            appDictValueDTO.setSort( String.valueOf( appDictValueDO.getSort() ) );
        }

        return appDictValueDTO;
    }

    @Override
    public AppDictValueDO to(AppDictValueDTO appDictValueDTO) {
        if ( appDictValueDTO == null ) {
            return null;
        }

        AppDictValueDO appDictValueDO = new AppDictValueDO();

        appDictValueDO.setId( appDictValueDTO.getId() );
        appDictValueDO.setDeleted( appDictValueDTO.getDeleted() );
        appDictValueDO.setName( appDictValueDTO.getName() );
        appDictValueDO.setValue( appDictValueDTO.getValue() );
        appDictValueDO.setRemark( appDictValueDTO.getRemark() );
        if ( appDictValueDTO.getSort() != null ) {
            appDictValueDO.setSort( Integer.parseInt( appDictValueDTO.getSort() ) );
        }

        return appDictValueDO;
    }

    @Override
    public List<AppDictValueDTO> from(List<AppDictValueDO> appDictValueDOS) {
        if ( appDictValueDOS == null ) {
            return null;
        }

        List<AppDictValueDTO> list = new ArrayList<AppDictValueDTO>( appDictValueDOS.size() );
        for ( AppDictValueDO appDictValueDO : appDictValueDOS ) {
            list.add( from( appDictValueDO ) );
        }

        return list;
    }

    @Override
    public List<AppDictValueDO> to(List<AppDictValueDTO> appDictValueDTOS) {
        if ( appDictValueDTOS == null ) {
            return null;
        }

        List<AppDictValueDO> list = new ArrayList<AppDictValueDO>( appDictValueDTOS.size() );
        for ( AppDictValueDTO appDictValueDTO : appDictValueDTOS ) {
            list.add( to( appDictValueDTO ) );
        }

        return list;
    }

    private String appDictValueDODictId(AppDictValueDO appDictValueDO) {
        if ( appDictValueDO == null ) {
            return null;
        }
        AppDictDO dict = appDictValueDO.getDict();
        if ( dict == null ) {
            return null;
        }
        String id = dict.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String appDictValueDOParentId(AppDictValueDO appDictValueDO) {
        if ( appDictValueDO == null ) {
            return null;
        }
        AppDictValueDO parent = appDictValueDO.getParent();
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
