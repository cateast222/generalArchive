package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.ArchivesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ArchivesMapper {

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "archiveType",target = "archiveType"),
            @Mapping(source = "parentId",target = "parentId"),
            @Mapping(source = "fileUrl",target = "fileUrl"),
            @Mapping(source = "archiveCode",target = "archiveCode"),
            @Mapping(source = "archiveFonds",target = "archiveFonds"),
            @Mapping(source = "entityType",target = "entityType"),
            @Mapping(source = "title",target = "title"),
            @Mapping(source = "archiveRow",target = "archiveRow"),
            @Mapping(source = "archiveColumn",target = "archiveColumn"),
            @Mapping(source = "archiveTime",target = "archiveTime"),
            @Mapping(source = "timeLimit",target = "timeLimit"),
            @Mapping(source = "archiveLevel",target = "archiveLevel"),
            @Mapping(source = "archiveStatus",target = "archiveStatus"),
            @Mapping(source = "fondsCode",target = "fondsCode"),
            @Mapping(source = "entityTypeCode",target = "entityTypeCode"),
            @Mapping(source = "organizationCode",target = "organizationCode"),
            @Mapping(source = "numberOfPages",target = "numberOfPages"),
            @Mapping(source = "c1",target="c1"),
            @Mapping(source = "c2",target="c2"),
            @Mapping(source = "c3",target="c3"),
            @Mapping(source = "c4",target="c4"),
            @Mapping(source = "c5",target="c5"),
            @Mapping(source = "c6",target="c6"),
            @Mapping(source = "c7",target="c7"),
            @Mapping(source = "c8",target="c8"),
            @Mapping(source = "c9",target="c9"),
            @Mapping(source = "c10",target="c10"),
            @Mapping(source = "c11",target="c11"),
            @Mapping(source = "c12",target="c12"),
            @Mapping(source = "c13",target="c13"),
            @Mapping(source = "c14",target="c14"),
            @Mapping(source = "c15",target="c15"),
            @Mapping(source = "c16",target="c16"),
            @Mapping(source = "c17",target="c17"),
            @Mapping(source = "c18",target="c18"),
            @Mapping(source = "c19",target="c19"),
            @Mapping(source = "c20",target="c20"),
            @Mapping(source = "c21",target="c21"),
            @Mapping(source = "c22",target="c22"),
            @Mapping(source = "c23",target="c23"),
            @Mapping(source = "c24",target="c24"),
            @Mapping(source = "c25",target="c25"),
            @Mapping(source = "c26",target="c26"),
            @Mapping(source = "c27",target="c27"),
            @Mapping(source = "c28",target="c28"),
            @Mapping(source = "c29",target="c29"),
            @Mapping(source = "c30",target="c30"),
            @Mapping(source = "c31",target="c31"),
            @Mapping(source = "c32",target="c32"),
            @Mapping(source = "c33",target="c33"),
            @Mapping(source = "c34",target="c34"),
            @Mapping(source = "c35",target="c35")
    })
    ArchivesDTO from(Archives archives);

    Archives to(ArchivesDTO archivesDTO);

    List<ArchivesDTO> from(List<Archives> archives);

    List<Archives> to(List<ArchivesDTO> ArchivesDTOs);
}
