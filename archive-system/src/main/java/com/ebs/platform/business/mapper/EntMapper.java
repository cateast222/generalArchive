package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.EntDO;
import com.ebs.platform.business.dto.EntDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 10:22
 */
@Mapper(componentModel = "spring")
public interface EntMapper {
    /**
     * @param entDO
     * @return
     */
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    public EntDTO from(EntDO entDO);

    /**
     * @param entDTO
     * @return
     */
    public EntDO to(EntDTO entDTO);

    /**
     * @param entDOS
     * @return
     */
    public List<EntDTO> from(List<EntDO> entDOS);

    /**
     * @param entDTOS
     * @return
     */
    public List<EntDO> to(List<EntDTO> entDTOS);
}
