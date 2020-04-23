package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveType;
import com.ebs.platform.business.dto.archive.ArchiveTypeParentDTO;
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
public class ArchiveTypeNodeMapperImpl implements ArchiveTypeNodeMapper {

    @Override
    public ArchiveTypeParentDTO from(ArchiveType archiveType) {
        if ( archiveType == null ) {
            return null;
        }

        ArchiveTypeParentDTO archiveTypeParentDTO = new ArchiveTypeParentDTO();

        archiveTypeParentDTO.setTypeName( archiveType.getTypeName() );
        archiveTypeParentDTO.setParentName( archiveType.getParentName() );
        archiveTypeParentDTO.setId( archiveType.getId() );
        archiveTypeParentDTO.setLabel( archiveType.getTypeName() );
        archiveTypeParentDTO.setParentCode( archiveType.getParentCode() );

        return archiveTypeParentDTO;
    }

    @Override
    public ArchiveType to(ArchiveTypeParentDTO archiveTypeParentDTO) {
        if ( archiveTypeParentDTO == null ) {
            return null;
        }

        ArchiveType archiveType = new ArchiveType();

        archiveType.setId( archiveTypeParentDTO.getId() );
        archiveType.setTypeName( archiveTypeParentDTO.getTypeName() );
        archiveType.setParentName( archiveTypeParentDTO.getParentName() );
        archiveType.setParentCode( archiveTypeParentDTO.getParentCode() );

        return archiveType;
    }

    @Override
    public List<ArchiveTypeParentDTO> from(List<ArchiveType> archiveTypes) {
        if ( archiveTypes == null ) {
            return null;
        }

        List<ArchiveTypeParentDTO> list = new ArrayList<ArchiveTypeParentDTO>( archiveTypes.size() );
        for ( ArchiveType archiveType : archiveTypes ) {
            list.add( from( archiveType ) );
        }

        return list;
    }

    @Override
    public List<ArchiveType> to(List<ArchiveTypeParentDTO> ArchiveTypeDTOs) {
        if ( ArchiveTypeDTOs == null ) {
            return null;
        }

        List<ArchiveType> list = new ArrayList<ArchiveType>( ArchiveTypeDTOs.size() );
        for ( ArchiveTypeParentDTO archiveTypeParentDTO : ArchiveTypeDTOs ) {
            list.add( to( archiveTypeParentDTO ) );
        }

        return list;
    }
}
