package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.EntityType;
import com.ebs.platform.business.dto.archive.EntityTypeNodeDTO;
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
public class EntityTypeNodeMapperImpl implements EntityTypeNodeMapper {

    @Override
    public EntityTypeNodeDTO from(EntityType entityType) {
        if ( entityType == null ) {
            return null;
        }

        EntityTypeNodeDTO entityTypeNodeDTO = new EntityTypeNodeDTO();

        entityTypeNodeDTO.setId( entityType.getId() );
        entityTypeNodeDTO.setLabel( entityType.getEntityName() );
        entityTypeNodeDTO.setParentId( entityType.getParentCode() );

        return entityTypeNodeDTO;
    }

    @Override
    public EntityType to(EntityTypeNodeDTO entityTypeDTO) {
        if ( entityTypeDTO == null ) {
            return null;
        }

        EntityType entityType = new EntityType();

        entityType.setId( entityTypeDTO.getId() );

        return entityType;
    }

    @Override
    public List<EntityTypeNodeDTO> from(List<EntityType> entityTypes) {
        if ( entityTypes == null ) {
            return null;
        }

        List<EntityTypeNodeDTO> list = new ArrayList<EntityTypeNodeDTO>( entityTypes.size() );
        for ( EntityType entityType : entityTypes ) {
            list.add( from( entityType ) );
        }

        return list;
    }

    @Override
    public List<EntityType> to(List<EntityTypeNodeDTO> entityTypeDTOS) {
        if ( entityTypeDTOS == null ) {
            return null;
        }

        List<EntityType> list = new ArrayList<EntityType>( entityTypeDTOS.size() );
        for ( EntityTypeNodeDTO entityTypeNodeDTO : entityTypeDTOS ) {
            list.add( to( entityTypeNodeDTO ) );
        }

        return list;
    }
}
