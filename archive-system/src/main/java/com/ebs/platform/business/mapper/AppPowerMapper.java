package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppPowerDO;
import com.ebs.platform.business.dto.AppPowerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 17:07
 */
@Mapper(componentModel = "spring")
public interface AppPowerMapper {
    /**
     * @param appPowerDO
     * @return
     */
    @Mappings({@Mapping(source = "parent.id",target = "parentId")})
    AppPowerDTO from(AppPowerDO appPowerDO);

    /**
     * @param appPowerDTO
     * @return
     */
    AppPowerDO to(AppPowerDTO appPowerDTO);

    /**
     * @param appPowerDOS
     * @return
     */
    List<AppPowerDTO> from(List<AppPowerDO> appPowerDOS);

    /**
     * @param appPowerDTOS
     * @return
     */
    List<AppPowerDO> to(List<AppPowerDTO> appPowerDTOS);
}
