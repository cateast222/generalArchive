package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppDO;
import com.ebs.platform.business.dto.AppDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 16:52
 */

@Mapper(componentModel = "spring")
public interface AppMapper {
    /**
     * @param appDO
     * @return
     */
    @Mappings({
            @Mapping(source = "id",target = "id")
    })
    public AppDTO from(AppDO appDO);

    /**
     * @param appDTO
     * @return
     */
    public AppDO to(AppDTO appDTO);

    /**
     * @param appDOS
     * @return
     */
    public List<AppDTO> from(List<AppDO> appDOS);

    /**
     * @param appDTOS
     * @return
     */
    public List<AppDO> to(List<AppDTO> appDTOS);
}
