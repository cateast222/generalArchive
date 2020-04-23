package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.NativePlaceDO;
import com.ebs.platform.business.dto.archive.NativePlaceDTO;
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
public class NativePlaceMapperImpl implements NativePlaceMapper {

    @Override
    public List<NativePlaceDTO> DOtoDTO(List<NativePlaceDO> nativePlaceDOS) {
        if ( nativePlaceDOS == null ) {
            return null;
        }

        List<NativePlaceDTO> list = new ArrayList<NativePlaceDTO>( nativePlaceDOS.size() );
        for ( NativePlaceDO nativePlaceDO : nativePlaceDOS ) {
            list.add( nativePlaceDOToNativePlaceDTO( nativePlaceDO ) );
        }

        return list;
    }

    protected NativePlaceDTO nativePlaceDOToNativePlaceDTO(NativePlaceDO nativePlaceDO) {
        if ( nativePlaceDO == null ) {
            return null;
        }

        NativePlaceDTO nativePlaceDTO = new NativePlaceDTO();

        nativePlaceDTO.setId( nativePlaceDO.getId() );
        nativePlaceDTO.setDeleted( nativePlaceDO.getDeleted() );
        nativePlaceDTO.setName( nativePlaceDO.getName() );
        nativePlaceDTO.setCode( nativePlaceDO.getCode() );
        nativePlaceDTO.setParentId( nativePlaceDO.getParentId() );
        nativePlaceDTO.setTheRank( nativePlaceDO.getTheRank() );
        nativePlaceDTO.setPinYin( nativePlaceDO.getPinYin() );

        return nativePlaceDTO;
    }
}
