package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveText;
import com.ebs.platform.business.dto.archivetext.ArchiveTextSaveDTO;
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
public class ArchiveTextMapperImpl implements ArchiveTextMapper {

    @Override
    public ArchiveTextSaveDTO from(ArchiveText archiveText) {
        if ( archiveText == null ) {
            return null;
        }

        ArchiveTextSaveDTO archiveTextSaveDTO = new ArchiveTextSaveDTO();

        archiveTextSaveDTO.setArchiveText( archiveText.getArchiveText() );
        archiveTextSaveDTO.setId( archiveText.getId() );
        archiveTextSaveDTO.setArchiveId( archiveText.getArchiveId() );

        return archiveTextSaveDTO;
    }

    @Override
    public ArchiveText to(ArchiveTextSaveDTO archiveTextDTO) {
        if ( archiveTextDTO == null ) {
            return null;
        }

        ArchiveText archiveText = new ArchiveText();

        archiveText.setId( archiveTextDTO.getId() );
        archiveText.setArchiveId( archiveTextDTO.getArchiveId() );
        archiveText.setArchiveText( archiveTextDTO.getArchiveText() );

        return archiveText;
    }

    @Override
    public List<ArchiveTextSaveDTO> from(List<ArchiveText> archiveText) {
        if ( archiveText == null ) {
            return null;
        }

        List<ArchiveTextSaveDTO> list = new ArrayList<ArchiveTextSaveDTO>( archiveText.size() );
        for ( ArchiveText archiveText1 : archiveText ) {
            list.add( from( archiveText1 ) );
        }

        return list;
    }

    @Override
    public List<ArchiveText> to(List<ArchiveTextSaveDTO> archiveTextDTOS) {
        if ( archiveTextDTOS == null ) {
            return null;
        }

        List<ArchiveText> list = new ArrayList<ArchiveText>( archiveTextDTOS.size() );
        for ( ArchiveTextSaveDTO archiveTextSaveDTO : archiveTextDTOS ) {
            list.add( to( archiveTextSaveDTO ) );
        }

        return list;
    }
}
