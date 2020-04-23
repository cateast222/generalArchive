package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.ArchivesShowDTO;
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
public class ArchivesShowMapperImpl implements ArchivesShowMapper {

    @Override
    public ArchivesShowDTO from(Archives archives) {
        if ( archives == null ) {
            return null;
        }

        ArchivesShowDTO archivesShowDTO = new ArchivesShowDTO();

        archivesShowDTO.setTimeLimit( archives.getTimeLimit() );
        archivesShowDTO.setArchiveTime( archives.getArchiveTime() );
        archivesShowDTO.setArchiveStatus( archives.getArchiveStatus() );
        archivesShowDTO.setArchiveCode( archives.getArchiveCode() );
        archivesShowDTO.setId( archives.getId() );
        archivesShowDTO.setTitle( archives.getTitle() );
        archivesShowDTO.setParentId( archives.getParentId() );
        archivesShowDTO.setArchiveFonds( archives.getArchiveFonds() );
        archivesShowDTO.setArchiveLevel( archives.getArchiveLevel() );

        return archivesShowDTO;
    }

    @Override
    public Archives to(ArchivesShowDTO archivesDTO) {
        if ( archivesDTO == null ) {
            return null;
        }

        Archives archives = new Archives();

        archives.setId( archivesDTO.getId() );
        archives.setParentId( archivesDTO.getParentId() );
        archives.setArchiveCode( archivesDTO.getArchiveCode() );
        archives.setArchiveFonds( archivesDTO.getArchiveFonds() );
        archives.setTitle( archivesDTO.getTitle() );
        archives.setArchiveTime( archivesDTO.getArchiveTime() );
        archives.setTimeLimit( archivesDTO.getTimeLimit() );
        archives.setArchiveLevel( archivesDTO.getArchiveLevel() );
        archives.setArchiveStatus( archivesDTO.getArchiveStatus() );

        return archives;
    }

    @Override
    public List<ArchivesShowDTO> from(List<Archives> archives) {
        if ( archives == null ) {
            return null;
        }

        List<ArchivesShowDTO> list = new ArrayList<ArchivesShowDTO>( archives.size() );
        for ( Archives archives1 : archives ) {
            list.add( from( archives1 ) );
        }

        return list;
    }

    @Override
    public List<Archives> to(List<ArchivesShowDTO> ArchivesDTOs) {
        if ( ArchivesDTOs == null ) {
            return null;
        }

        List<Archives> list = new ArrayList<Archives>( ArchivesDTOs.size() );
        for ( ArchivesShowDTO archivesShowDTO : ArchivesDTOs ) {
            list.add( to( archivesShowDTO ) );
        }

        return list;
    }
}
