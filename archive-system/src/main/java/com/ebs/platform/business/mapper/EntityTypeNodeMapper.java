package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.EntityType;
import com.ebs.platform.business.dto.archive.EntityTypeDTO;
import com.ebs.platform.business.dto.archive.EntityTypeNodeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface EntityTypeNodeMapper {

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "entityName",target = "label"),
            @Mapping(source = "parentCode",target = "parentId")
    })
    EntityTypeNodeDTO from(EntityType entityType);

    EntityType to(EntityTypeNodeDTO entityTypeDTO);

    List<EntityTypeNodeDTO> from(List<EntityType> entityTypes);

    List<EntityType> to(List<EntityTypeNodeDTO> entityTypeDTOS);
}
