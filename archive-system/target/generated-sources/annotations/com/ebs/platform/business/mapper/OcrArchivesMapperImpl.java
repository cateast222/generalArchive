package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.OcrArchivesDTO;
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
public class OcrArchivesMapperImpl implements OcrArchivesMapper {

    @Override
    public OcrArchivesDTO from(Archives accountDO) {
        if ( accountDO == null ) {
            return null;
        }

        OcrArchivesDTO ocrArchivesDTO = new OcrArchivesDTO();

        ocrArchivesDTO.setArchiveCode( accountDO.getArchiveCode() );
        ocrArchivesDTO.setArchiveType( accountDO.getArchiveType() );
        ocrArchivesDTO.setTitle( accountDO.getTitle() );

        return ocrArchivesDTO;
    }

    @Override
    public Archives to(OcrArchivesDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        Archives archives = new Archives();

        archives.setArchiveType( accountDTO.getArchiveType() );
        archives.setArchiveCode( accountDTO.getArchiveCode() );
        archives.setTitle( accountDTO.getTitle() );

        return archives;
    }

    @Override
    public List<OcrArchivesDTO> from(List<Archives> accountDOS) {
        if ( accountDOS == null ) {
            return null;
        }

        List<OcrArchivesDTO> list = new ArrayList<OcrArchivesDTO>( accountDOS.size() );
        for ( Archives archives : accountDOS ) {
            list.add( from( archives ) );
        }

        return list;
    }

    @Override
    public List<Archives> to(List<OcrArchivesDTO> accountDTOS) {
        if ( accountDTOS == null ) {
            return null;
        }

        List<Archives> list = new ArrayList<Archives>( accountDTOS.size() );
        for ( OcrArchivesDTO ocrArchivesDTO : accountDTOS ) {
            list.add( to( ocrArchivesDTO ) );
        }

        return list;
    }
}
