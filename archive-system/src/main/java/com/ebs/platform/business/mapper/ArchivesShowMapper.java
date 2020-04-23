package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.ArchivesDTO;
import com.ebs.platform.business.dto.archive.ArchivesShowDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ArchivesShowMapper {

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "parentId",target = "parentId"),
            @Mapping(source = "archiveCode",target = "archiveCode"),
            @Mapping(source = "archiveFonds",target = "archiveFonds"),
            @Mapping(source = "archiveLevel",target = "archiveLevel"),
            @Mapping(source = "title",target = "title"),
            @Mapping(source = "archiveTime",target = "archiveTime"),
            @Mapping(source = "timeLimit",target = "timeLimit"),
            @Mapping(source = "archiveStatus",target = "archiveStatus")
    })
    ArchivesShowDTO from(Archives archives);

    Archives to(ArchivesShowDTO archivesDTO);

    List<ArchivesShowDTO> from(List<Archives> archives);

    List<Archives> to(List<ArchivesShowDTO> ArchivesDTOs);
}
