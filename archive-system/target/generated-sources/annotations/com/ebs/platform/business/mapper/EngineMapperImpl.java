package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.EngineDO;
import com.ebs.platform.business.dto.engine.EngineDTO;
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
public class EngineMapperImpl implements EngineMapper {

    @Override
    public EngineDTO from(EngineDO appDO) {
        if ( appDO == null ) {
            return null;
        }

        EngineDTO engineDTO = new EngineDTO();

        engineDTO.setId( appDO.getId() );
        engineDTO.setName( appDO.getName() );
        engineDTO.setCode( appDO.getCode() );
        engineDTO.setAppId( appDO.getAppId() );
        engineDTO.setContent( appDO.getContent() );
        engineDTO.setRemark( appDO.getRemark() );
        engineDTO.setCreateDate( appDO.getCreateDate() );
        engineDTO.setUserId( appDO.getUserId() );

        return engineDTO;
    }

    @Override
    public EngineDO to(EngineDTO appDTO) {
        if ( appDTO == null ) {
            return null;
        }

        EngineDO engineDO = new EngineDO();

        engineDO.setId( appDTO.getId() );
        engineDO.setName( appDTO.getName() );
        engineDO.setCode( appDTO.getCode() );
        engineDO.setAppId( appDTO.getAppId() );
        engineDO.setContent( appDTO.getContent() );
        engineDO.setRemark( appDTO.getRemark() );
        engineDO.setCreateDate( appDTO.getCreateDate() );
        engineDO.setUserId( appDTO.getUserId() );

        return engineDO;
    }

    @Override
    public List<EngineDTO> from(List<EngineDO> appDOS) {
        if ( appDOS == null ) {
            return null;
        }

        List<EngineDTO> list = new ArrayList<EngineDTO>( appDOS.size() );
        for ( EngineDO engineDO : appDOS ) {
            list.add( from( engineDO ) );
        }

        return list;
    }

    @Override
    public List<EngineDO> to(List<EngineDTO> appDTOS) {
        if ( appDTOS == null ) {
            return null;
        }

        List<EngineDO> list = new ArrayList<EngineDO>( appDTOS.size() );
        for ( EngineDTO engineDTO : appDTOS ) {
            list.add( to( engineDTO ) );
        }

        return list;
    }
}
