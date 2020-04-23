package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.NativePlaceDO;
import com.ebs.platform.business.domain.UnitDO;
import com.ebs.platform.business.dto.archive.NativePlaceDTO;
import com.ebs.platform.business.dto.archive.UnitDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author lwy
 * @Date 2019-05-29 10:43
 */
@Mapper(componentModel = "spring")
public interface NativePlaceMapper {

    List<NativePlaceDTO> DOtoDTO(List<NativePlaceDO> nativePlaceDOS);
}
