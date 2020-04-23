package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.ArchivesDTO;
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
public class ArchivesMapperImpl implements ArchivesMapper {

    @Override
    public ArchivesDTO from(Archives archives) {
        if ( archives == null ) {
            return null;
        }

        ArchivesDTO archivesDTO = new ArchivesDTO();

        archivesDTO.setC31( archives.getC31() );
        archivesDTO.setC30( archives.getC30() );
        archivesDTO.setC33( archives.getC33() );
        archivesDTO.setC32( archives.getC32() );
        archivesDTO.setC35( archives.getC35() );
        archivesDTO.setC34( archives.getC34() );
        archivesDTO.setArchiveCode( archives.getArchiveCode() );
        archivesDTO.setId( archives.getId() );
        archivesDTO.setEntityType( archives.getEntityType() );
        archivesDTO.setC1( archives.getC1() );
        archivesDTO.setC2( archives.getC2() );
        archivesDTO.setC3( archives.getC3() );
        archivesDTO.setC4( archives.getC4() );
        archivesDTO.setC5( archives.getC5() );
        archivesDTO.setC6( archives.getC6() );
        archivesDTO.setC7( archives.getC7() );
        archivesDTO.setOrganizationCode( archives.getOrganizationCode() );
        archivesDTO.setC8( archives.getC8() );
        archivesDTO.setC9( archives.getC9() );
        archivesDTO.setArchiveStatus( archives.getArchiveStatus() );
        archivesDTO.setC11( archives.getC11() );
        archivesDTO.setC10( archives.getC10() );
        archivesDTO.setC13( archives.getC13() );
        archivesDTO.setC12( archives.getC12() );
        archivesDTO.setC15( archives.getC15() );
        archivesDTO.setC14( archives.getC14() );
        archivesDTO.setC17( archives.getC17() );
        archivesDTO.setC16( archives.getC16() );
        archivesDTO.setC19( archives.getC19() );
        archivesDTO.setC18( archives.getC18() );
        archivesDTO.setTitle( archives.getTitle() );
        archivesDTO.setFondsCode( archives.getFondsCode() );
        archivesDTO.setArchiveFonds( archives.getArchiveFonds() );
        archivesDTO.setArchiveType( archives.getArchiveType() );
        archivesDTO.setNumberOfPages( archives.getNumberOfPages() );
        archivesDTO.setFileUrl( archives.getFileUrl() );
        archivesDTO.setC20( archives.getC20() );
        archivesDTO.setArchiveTime( archives.getArchiveTime() );
        archivesDTO.setC22( archives.getC22() );
        archivesDTO.setC21( archives.getC21() );
        archivesDTO.setC24( archives.getC24() );
        archivesDTO.setArchiveColumn( archives.getArchiveColumn() );
        archivesDTO.setC23( archives.getC23() );
        archivesDTO.setC26( archives.getC26() );
        archivesDTO.setC25( archives.getC25() );
        archivesDTO.setC28( archives.getC28() );
        archivesDTO.setC27( archives.getC27() );
        archivesDTO.setC29( archives.getC29() );
        archivesDTO.setParentId( archives.getParentId() );
        archivesDTO.setArchiveLevel( archives.getArchiveLevel() );
        archivesDTO.setTimeLimit( archives.getTimeLimit() );
        archivesDTO.setEntityTypeCode( archives.getEntityTypeCode() );
        archivesDTO.setArchiveRow( archives.getArchiveRow() );

        return archivesDTO;
    }

    @Override
    public Archives to(ArchivesDTO archivesDTO) {
        if ( archivesDTO == null ) {
            return null;
        }

        Archives archives = new Archives();

        archives.setId( archivesDTO.getId() );
        archives.setArchiveType( archivesDTO.getArchiveType() );
        archives.setParentId( archivesDTO.getParentId() );
        archives.setFileUrl( archivesDTO.getFileUrl() );
        archives.setArchiveCode( archivesDTO.getArchiveCode() );
        archives.setArchiveFonds( archivesDTO.getArchiveFonds() );
        archives.setEntityType( archivesDTO.getEntityType() );
        archives.setTitle( archivesDTO.getTitle() );
        archives.setArchiveRow( archivesDTO.getArchiveRow() );
        archives.setArchiveColumn( archivesDTO.getArchiveColumn() );
        archives.setArchiveTime( archivesDTO.getArchiveTime() );
        archives.setTimeLimit( archivesDTO.getTimeLimit() );
        archives.setArchiveLevel( archivesDTO.getArchiveLevel() );
        archives.setArchiveStatus( archivesDTO.getArchiveStatus() );
        archives.setFondsCode( archivesDTO.getFondsCode() );
        archives.setEntityTypeCode( archivesDTO.getEntityTypeCode() );
        archives.setOrganizationCode( archivesDTO.getOrganizationCode() );
        archives.setNumberOfPages( archivesDTO.getNumberOfPages() );
        archives.setC1( archivesDTO.getC1() );
        archives.setC2( archivesDTO.getC2() );
        archives.setC3( archivesDTO.getC3() );
        archives.setC4( archivesDTO.getC4() );
        archives.setC5( archivesDTO.getC5() );
        archives.setC6( archivesDTO.getC6() );
        archives.setC7( archivesDTO.getC7() );
        archives.setC8( archivesDTO.getC8() );
        archives.setC9( archivesDTO.getC9() );
        archives.setC10( archivesDTO.getC10() );
        archives.setC11( archivesDTO.getC11() );
        archives.setC12( archivesDTO.getC12() );
        archives.setC13( archivesDTO.getC13() );
        archives.setC14( archivesDTO.getC14() );
        archives.setC15( archivesDTO.getC15() );
        archives.setC16( archivesDTO.getC16() );
        archives.setC17( archivesDTO.getC17() );
        archives.setC18( archivesDTO.getC18() );
        archives.setC19( archivesDTO.getC19() );
        archives.setC20( archivesDTO.getC20() );
        archives.setC21( archivesDTO.getC21() );
        archives.setC22( archivesDTO.getC22() );
        archives.setC23( archivesDTO.getC23() );
        archives.setC24( archivesDTO.getC24() );
        archives.setC25( archivesDTO.getC25() );
        archives.setC26( archivesDTO.getC26() );
        archives.setC27( archivesDTO.getC27() );
        archives.setC28( archivesDTO.getC28() );
        archives.setC29( archivesDTO.getC29() );
        archives.setC30( archivesDTO.getC30() );
        archives.setC31( archivesDTO.getC31() );
        archives.setC32( archivesDTO.getC32() );
        archives.setC33( archivesDTO.getC33() );
        archives.setC34( archivesDTO.getC34() );
        archives.setC35( archivesDTO.getC35() );

        return archives;
    }

    @Override
    public List<ArchivesDTO> from(List<Archives> archives) {
        if ( archives == null ) {
            return null;
        }

        List<ArchivesDTO> list = new ArrayList<ArchivesDTO>( archives.size() );
        for ( Archives archives1 : archives ) {
            list.add( from( archives1 ) );
        }

        return list;
    }

    @Override
    public List<Archives> to(List<ArchivesDTO> ArchivesDTOs) {
        if ( ArchivesDTOs == null ) {
            return null;
        }

        List<Archives> list = new ArrayList<Archives>( ArchivesDTOs.size() );
        for ( ArchivesDTO archivesDTO : ArchivesDTOs ) {
            list.add( to( archivesDTO ) );
        }

        return list;
    }
}
