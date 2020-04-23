package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.EngineDO;
import com.ebs.platform.business.dto.engine.EngineDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
/**
 * 引擎对象映射
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 16:52
 */

import java.util.List;

@Mapper(componentModel = "spring")
public interface EngineMapper {

    /**
     * @param appDO
     * @return
     */
    @Mappings({
            @Mapping(source = "id",target = "id")
    })
    EngineDTO from(EngineDO appDO);

    /**
     *
     * @param appDTO
     * @return
     */
    EngineDO to(EngineDTO appDTO);

    /**
     *
     * @param appDOS
     * @return
     */
    List<EngineDTO> from(List<EngineDO> appDOS);

    /**
     *
     * @param appDTOS
     * @return
     */
    List<EngineDO> to(List<EngineDTO> appDTOS);


}
