package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppDictDO;
import com.ebs.platform.core.dto.AppDictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 9:07
 */

// 只需要 DO 转DTO 需要 手动映射 ；  DTO 无法直接转为 DO, 比如一个  DTO 里的一个ID ,但是在 DO 里面是 一个 类对象
@Mapper(componentModel = "spring")
public interface AppDictMapper {
    /**
     * @param appDictDO
     * @return
     */
    @Mappings({
            @Mapping(source = "app.id",target = "appId")
            
    })
    AppDictDTO from(AppDictDO appDictDO);

    /**
     * @param appDictDTO
     * @return
     */
    AppDictDO to(AppDictDTO appDictDTO);

    /**
     * @param appDictDOS
     * @return
     */
    List<AppDictDTO> from(List<AppDictDO> appDictDOS);

    /**
     * @param appDictDTOS
     * @return
     */
    List<AppDictDO> to(List<AppDictDTO> appDictDTOS);
}
