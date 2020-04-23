package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AccountDO;
import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.AccountDTO;
import com.ebs.platform.business.dto.archive.OcrArchivesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OcrArchivesMapper {

    @Mappings({
            @Mapping(source = "archiveType",target = "archiveType"),
            @Mapping(source = "archiveCode",target = "archiveCode"),
            @Mapping(source = "title",target = "title")
    })
    OcrArchivesDTO from(Archives accountDO);

    Archives to(OcrArchivesDTO accountDTO);

    List<OcrArchivesDTO> from(List<Archives> accountDOS);

    List<Archives> to(List<OcrArchivesDTO> accountDTOS);
}
