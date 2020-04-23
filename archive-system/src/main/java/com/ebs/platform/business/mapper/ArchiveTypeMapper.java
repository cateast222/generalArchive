package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveType;
import com.ebs.platform.business.dto.archive.ArchiveTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ArchiveTypeMapper {

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "typeName",target = "typeName"),
            @Mapping(source = "typeName",target = "title"),
            @Mapping(source = "parentCode",target = "parentCode"),
            @Mapping(source = "typeLayer",target = "typeLayer"),
            @Mapping(source = "typeDesc",target = "typeDesc"),
            @Mapping(source = "typeOrder",target = "typeOrder"),
            @Mapping(source = "parentName",target = "parentName")
    })
    ArchiveTypeDTO from(ArchiveType archiveType);

    ArchiveType to(ArchiveTypeDTO accountDTO);

    List<ArchiveTypeDTO> from(List<ArchiveType> accountDOS);

    List<ArchiveType> to(List<ArchiveTypeDTO> ArchiveTypeDTOs);
}
