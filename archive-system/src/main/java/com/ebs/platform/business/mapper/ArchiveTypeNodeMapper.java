package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveType;
import com.ebs.platform.business.dto.archive.ArchiveTypeDTO;
import com.ebs.platform.business.dto.archive.ArchiveTypeParentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ArchiveTypeNodeMapper {

    @Mappings({
            @Mapping(source = "typeName",target = "typeName"),
            @Mapping(source = "parentCode",target = "parentCode"),
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "typeName",target = "label"),
            @Mapping(source = "parentName",target = "parentName")
    })
    ArchiveTypeParentDTO from(ArchiveType archiveType);

    ArchiveType to(ArchiveTypeParentDTO archiveTypeParentDTO);

    List<ArchiveTypeParentDTO> from(List<ArchiveType> archiveTypes);

    List<ArchiveType> to(List<ArchiveTypeParentDTO> ArchiveTypeDTOs);
}
