package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.EntDO;
import com.ebs.platform.business.domain.EntDeptDO;
import com.ebs.platform.business.dto.EntDeptDTO;
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
public class EntDeptMapperImpl implements EntDeptMapper {

    @Override
    public EntDeptDTO from(EntDeptDO entDeptDO) {
        if ( entDeptDO == null ) {
            return null;
        }

        EntDeptDTO entDeptDTO = new EntDeptDTO();

        String id = entDeptDOEnterpriseId( entDeptDO );
        if ( id != null ) {
            entDeptDTO.setEntId( id );
        }
        String id1 = entDeptDOParentId( entDeptDO );
        if ( id1 != null ) {
            entDeptDTO.setParentId( id1 );
        }
        entDeptDTO.setId( entDeptDO.getId() );
        entDeptDTO.setDeleted( entDeptDO.getDeleted() );
        entDeptDTO.setName( entDeptDO.getName() );

        return entDeptDTO;
    }

    @Override
    public EntDeptDO to(EntDeptDTO entDeptDTO) {
        if ( entDeptDTO == null ) {
            return null;
        }

        EntDeptDO entDeptDO = new EntDeptDO();

        entDeptDO.setId( entDeptDTO.getId() );
        entDeptDO.setDeleted( entDeptDTO.getDeleted() );
        entDeptDO.setName( entDeptDTO.getName() );

        return entDeptDO;
    }

    @Override
    public List<EntDeptDTO> from(List<EntDeptDO> entDeptDOS) {
        if ( entDeptDOS == null ) {
            return null;
        }

        List<EntDeptDTO> list = new ArrayList<EntDeptDTO>( entDeptDOS.size() );
        for ( EntDeptDO entDeptDO : entDeptDOS ) {
            list.add( from( entDeptDO ) );
        }

        return list;
    }

    @Override
    public List<EntDeptDO> to(List<EntDeptDTO> entDeptDTOS) {
        if ( entDeptDTOS == null ) {
            return null;
        }

        List<EntDeptDO> list = new ArrayList<EntDeptDO>( entDeptDTOS.size() );
        for ( EntDeptDTO entDeptDTO : entDeptDTOS ) {
            list.add( to( entDeptDTO ) );
        }

        return list;
    }

    private String entDeptDOEnterpriseId(EntDeptDO entDeptDO) {
        if ( entDeptDO == null ) {
            return null;
        }
        EntDO enterprise = entDeptDO.getEnterprise();
        if ( enterprise == null ) {
            return null;
        }
        String id = enterprise.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entDeptDOParentId(EntDeptDO entDeptDO) {
        if ( entDeptDO == null ) {
            return null;
        }
        EntDeptDO parent = entDeptDO.getParent();
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
