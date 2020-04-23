package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.UnitDO;
import com.ebs.platform.business.dto.archive.UnitDTO;
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
public class UnitMapperImpl implements UnitMapper {

    @Override
    public UnitDO DTOtoDO(UnitDTO unitDTO) {
        if ( unitDTO == null ) {
            return null;
        }

        UnitDO unitDO = new UnitDO();

        unitDO.setId( unitDTO.getId() );
        unitDO.setUnitClassName( unitDTO.getUnitClassName() );
        unitDO.setDictValueId( unitDTO.getDictValueId() );
        unitDO.setName( unitDTO.getName() );
        unitDO.setCode( unitDTO.getCode() );
        unitDO.setSimpleName( unitDTO.getSimpleName() );
        unitDO.setSort( unitDTO.getSort() );
        unitDO.setReportName( unitDTO.getReportName() );
        unitDO.setReportCode( unitDTO.getReportCode() );
        unitDO.setRemark( unitDTO.getRemark() );

        return unitDO;
    }

    @Override
    public UnitDTO DOtoDTO(UnitDO unitDO) {
        if ( unitDO == null ) {
            return null;
        }

        UnitDTO unitDTO = new UnitDTO();

        unitDTO.setId( unitDO.getId() );
        unitDTO.setUnitClassName( unitDO.getUnitClassName() );
        unitDTO.setDictValueId( unitDO.getDictValueId() );
        unitDTO.setName( unitDO.getName() );
        unitDTO.setCode( unitDO.getCode() );
        unitDTO.setSimpleName( unitDO.getSimpleName() );
        unitDTO.setSort( unitDO.getSort() );
        unitDTO.setReportName( unitDO.getReportName() );
        unitDTO.setReportCode( unitDO.getReportCode() );
        unitDTO.setRemark( unitDO.getRemark() );

        return unitDTO;
    }

    @Override
    public List<UnitDTO> DOtoDTO(List<UnitDO> unitDO) {
        if ( unitDO == null ) {
            return null;
        }

        List<UnitDTO> list = new ArrayList<UnitDTO>( unitDO.size() );
        for ( UnitDO unitDO1 : unitDO ) {
            list.add( DOtoDTO( unitDO1 ) );
        }

        return list;
    }
}
