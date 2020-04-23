package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.EntDeptDO;
import com.ebs.platform.business.dto.EntDeptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 10:30
 */
@Mapper(componentModel = "spring")
public interface EntDeptMapper {
    /**
     * @param entDeptDO
     * @return
     */
    @Mappings({
            @Mapping(source = "enterprise.id",target = "entId"),
            @Mapping(source = "parent.id",target = "parentId"),
    })
    public EntDeptDTO from(EntDeptDO entDeptDO);

    /**
     * @param entDeptDTO
     * @return
     */
    public EntDeptDO to(EntDeptDTO entDeptDTO);

    /**
     * @param entDeptDOS
     * @return
     */
    public List<EntDeptDTO> from(List<EntDeptDO> entDeptDOS);

    /**
     * @param entDeptDTOS
     * @return
     */
    public List<EntDeptDO> to(List<EntDeptDTO> entDeptDTOS);
}
