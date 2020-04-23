package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.EntDO;
import com.ebs.platform.business.dto.EntDTO;
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
public class EntMapperImpl implements EntMapper {

    @Override
    public EntDTO from(EntDO entDO) {
        if ( entDO == null ) {
            return null;
        }

        EntDTO entDTO = new EntDTO();

        String id = entDOParentId( entDO );
        if ( id != null ) {
            entDTO.setParentId( id );
        }
        entDTO.setId( entDO.getId() );
        entDTO.setDeleted( entDO.getDeleted() );
        entDTO.setName( entDO.getName() );

        return entDTO;
    }

    @Override
    public EntDO to(EntDTO entDTO) {
        if ( entDTO == null ) {
            return null;
        }

        EntDO entDO = new EntDO();

        entDO.setId( entDTO.getId() );
        entDO.setDeleted( entDTO.getDeleted() );
        entDO.setName( entDTO.getName() );

        return entDO;
    }

    @Override
    public List<EntDTO> from(List<EntDO> entDOS) {
        if ( entDOS == null ) {
            return null;
        }

        List<EntDTO> list = new ArrayList<EntDTO>( entDOS.size() );
        for ( EntDO entDO : entDOS ) {
            list.add( from( entDO ) );
        }

        return list;
    }

    @Override
    public List<EntDO> to(List<EntDTO> entDTOS) {
        if ( entDTOS == null ) {
            return null;
        }

        List<EntDO> list = new ArrayList<EntDO>( entDTOS.size() );
        for ( EntDTO entDTO : entDTOS ) {
            list.add( to( entDTO ) );
        }

        return list;
    }

    private String entDOParentId(EntDO entDO) {
        if ( entDO == null ) {
            return null;
        }
        EntDO parent = entDO.getParent();
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
