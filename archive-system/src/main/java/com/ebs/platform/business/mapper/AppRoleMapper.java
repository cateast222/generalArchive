package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppRoleDO;
import com.ebs.platform.business.dto.AppRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 9:00
 */
@Mapper(componentModel = "spring")
public interface AppRoleMapper {
    /**
     * @param appRoleDO
     * @return
     */
    @Mappings({
            @Mapping(source = "tenant.id",target = "tenantId")
    })
    public AppRoleDTO from(AppRoleDO appRoleDO);

    /**
     * @param appRoleDTO
     * @return
     */
    public AppRoleDO to(AppRoleDTO appRoleDTO);

    /**
     * @param appRoleDOS
     * @return
     */
    public List<AppRoleDTO> from(List<AppRoleDO> appRoleDOS);

    /**
     * @param appRoleDTOS
     * @return
     */
    public List<AppRoleDO> to(List<AppRoleDTO> appRoleDTOS);
}
