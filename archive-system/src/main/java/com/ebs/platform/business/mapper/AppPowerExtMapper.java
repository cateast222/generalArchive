package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppPowerExtDO;
import com.ebs.platform.business.dto.AppPowerExtDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 8:38
 */
@Mapper(componentModel = "spring")
public interface AppPowerExtMapper {
    /**
     * @param appPowerExtDO
     * @return
     */
    @Mappings({
            @Mapping(source = "id",target = "id")
    })
    public AppPowerExtDTO from(AppPowerExtDO appPowerExtDO);

    /**
     * @param appPowerExtDTO
     * @return
     */
    public AppPowerExtDO to(AppPowerExtDTO appPowerExtDTO);

    /**
     * @param appPowerExtDOS
     * @return
     */
    public List<AppPowerExtDTO> from(List<AppPowerExtDO> appPowerExtDOS);

    /**
     * @param appPowerExtDTOS
     * @return
     */
    public List<AppPowerExtDO> to(List<AppPowerExtDTO> appPowerExtDTOS);
}
