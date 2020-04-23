package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppDictValueDO;
import com.ebs.platform.core.dto.AppDictValueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 9:30
 */
@Mapper(componentModel = "spring")
public interface AppDictValueMapper {
    /**
     * @param appDictValueDO
     * @return
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "parent.id", target = "parentId"),
            @Mapping(source = "dict.id", target = "dictId")
    })
    AppDictValueDTO from(AppDictValueDO appDictValueDO);

    /**
     * @param appDictValueDTO
     * @return
     */
    AppDictValueDO to(AppDictValueDTO appDictValueDTO);

    /**
     * @param appDictValueDOS
     * @return
     */
    List<AppDictValueDTO> from(List<AppDictValueDO> appDictValueDOS);

    /**
     * @param appDictValueDTOS
     * @return
     */
    List<AppDictValueDO> to(List<AppDictValueDTO> appDictValueDTOS);

}
