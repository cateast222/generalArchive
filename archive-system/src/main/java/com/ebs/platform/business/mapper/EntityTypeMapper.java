package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveType;
import com.ebs.platform.business.domain.professionentity.EntityType;
import com.ebs.platform.business.dto.archive.ArchiveTypeDTO;
import com.ebs.platform.business.dto.archive.EntityTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface EntityTypeMapper {

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "entityName",target = "entityName"),
            @Mapping(source = "entityCode",target = "entityCode"),
            @Mapping(source = "parentCode",target = "parentCode"),
            @Mapping(source = "entityOrder",target = "entityOrder"),
            @Mapping(source = "entityDesc",target = "entityDesc"),
            @Mapping(source = "entityFonds",target = "entityFonds")
    })
    EntityTypeDTO from(EntityType entityType);

    EntityType to(EntityTypeDTO entityTypeDTO);

    List<EntityTypeDTO> from(List<EntityType> entityTypes);

    List<EntityType> to(List<EntityTypeDTO> entityTypeDTOS);
}
