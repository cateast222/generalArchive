package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveText;
import com.ebs.platform.business.dto.archivetext.ArchiveTextSaveDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ArchiveTextMapper{

    @Mappings({
            @Mapping(source= "id",target = "id"),
            @Mapping(source= "archiveText",target = "archiveText"),
            @Mapping(source= "archiveId",target = "archiveId")
    })
    ArchiveTextSaveDTO from(ArchiveText archiveText);
    ArchiveText to(ArchiveTextSaveDTO archiveTextDTO);
    List<ArchiveTextSaveDTO> from(List<ArchiveText> archiveText);
    List<ArchiveText> to(List<ArchiveTextSaveDTO> archiveTextDTOS);

}
