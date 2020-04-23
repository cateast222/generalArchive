package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.EntityType;
import com.ebs.platform.business.dto.archive.EntityTypeDTO;
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
public class EntityTypeMapperImpl implements EntityTypeMapper {

    @Override
    public EntityTypeDTO from(EntityType entityType) {
        if ( entityType == null ) {
            return null;
        }

        EntityTypeDTO entityTypeDTO = new EntityTypeDTO();

        entityTypeDTO.setEntityCode( entityType.getEntityCode() );
        entityTypeDTO.setEntityFonds( entityType.getEntityFonds() );
        entityTypeDTO.setParentCode( entityType.getParentCode() );
        entityTypeDTO.setEntityName( entityType.getEntityName() );
        entityTypeDTO.setEntityDesc( entityType.getEntityDesc() );
        entityTypeDTO.setEntityOrder( entityType.getEntityOrder() );
        entityTypeDTO.setId( entityType.getId() );

        return entityTypeDTO;
    }

    @Override
    public EntityType to(EntityTypeDTO entityTypeDTO) {
        if ( entityTypeDTO == null ) {
            return null;
        }

        EntityType entityType = new EntityType();

        entityType.setId( entityTypeDTO.getId() );
        entityType.setEntityName( entityTypeDTO.getEntityName() );
        entityType.setEntityCode( entityTypeDTO.getEntityCode() );
        entityType.setParentCode( entityTypeDTO.getParentCode() );
        entityType.setEntityOrder( entityTypeDTO.getEntityOrder() );
        entityType.setEntityDesc( entityTypeDTO.getEntityDesc() );
        entityType.setEntityFonds( entityTypeDTO.getEntityFonds() );

        return entityType;
    }

    @Override
    public List<EntityTypeDTO> from(List<EntityType> entityTypes) {
        if ( entityTypes == null ) {
            return null;
        }

        List<EntityTypeDTO> list = new ArrayList<EntityTypeDTO>( entityTypes.size() );
        for ( EntityType entityType : entityTypes ) {
            list.add( from( entityType ) );
        }

        return list;
    }

    @Override
    public List<EntityType> to(List<EntityTypeDTO> entityTypeDTOS) {
        if ( entityTypeDTOS == null ) {
            return null;
        }

        List<EntityType> list = new ArrayList<EntityType>( entityTypeDTOS.size() );
        for ( EntityTypeDTO entityTypeDTO : entityTypeDTOS ) {
            list.add( to( entityTypeDTO ) );
        }

        return list;
    }
}
