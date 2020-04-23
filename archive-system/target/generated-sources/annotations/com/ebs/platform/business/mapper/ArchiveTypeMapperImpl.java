package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.ArchiveType;
import com.ebs.platform.business.dto.archive.ArchiveTypeDTO;
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
public class ArchiveTypeMapperImpl implements ArchiveTypeMapper {

    @Override
    public ArchiveTypeDTO from(ArchiveType archiveType) {
        if ( archiveType == null ) {
            return null;
        }

        ArchiveTypeDTO archiveTypeDTO = new ArchiveTypeDTO();

        archiveTypeDTO.setParentName( archiveType.getParentName() );
        archiveTypeDTO.setTypeDesc( archiveType.getTypeDesc() );
        archiveTypeDTO.setParentCode( archiveType.getParentCode() );
        archiveTypeDTO.setTypeOrder( archiveType.getTypeOrder() );
        archiveTypeDTO.setTypeLayer( archiveType.getTypeLayer() );
        archiveTypeDTO.setTypeName( archiveType.getTypeName() );
        archiveTypeDTO.setId( archiveType.getId() );
        archiveTypeDTO.setTitle( archiveType.getTypeName() );

        return archiveTypeDTO;
    }

    @Override
    public ArchiveType to(ArchiveTypeDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        ArchiveType archiveType = new ArchiveType();

        archiveType.setId( accountDTO.getId() );
        archiveType.setTypeName( accountDTO.getTypeName() );
        archiveType.setParentName( accountDTO.getParentName() );
        archiveType.setParentCode( accountDTO.getParentCode() );
        archiveType.setTypeLayer( accountDTO.getTypeLayer() );
        archiveType.setTypeOrder( accountDTO.getTypeOrder() );
        archiveType.setTypeDesc( accountDTO.getTypeDesc() );

        return archiveType;
    }

    @Override
    public List<ArchiveTypeDTO> from(List<ArchiveType> accountDOS) {
        if ( accountDOS == null ) {
            return null;
        }

        List<ArchiveTypeDTO> list = new ArrayList<ArchiveTypeDTO>( accountDOS.size() );
        for ( ArchiveType archiveType : accountDOS ) {
            list.add( from( archiveType ) );
        }

        return list;
    }

    @Override
    public List<ArchiveType> to(List<ArchiveTypeDTO> ArchiveTypeDTOs) {
        if ( ArchiveTypeDTOs == null ) {
            return null;
        }

        List<ArchiveType> list = new ArrayList<ArchiveType>( ArchiveTypeDTOs.size() );
        for ( ArchiveTypeDTO archiveTypeDTO : ArchiveTypeDTOs ) {
            list.add( to( archiveTypeDTO ) );
        }

        return list;
    }
}
