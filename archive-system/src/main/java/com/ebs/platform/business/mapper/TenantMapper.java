package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.TenantDO;
import com.ebs.platform.business.dto.TenantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 10:51
 */
@Mapper(componentModel = "spring")
public interface TenantMapper {
    /**
     * @param tenantDO
     * @return
     */
    @Mappings({
            @Mapping(source = "app.id",target = "appId"),
            @Mapping(source = "enterprise.id",target = "rentId")            
    })
    public TenantDTO from(TenantDO tenantDO);

    /**
     * @param tenantDTO
     * @return
     */
    public TenantDO to(TenantDTO tenantDTO);

    /**
     * @param tenantDOS
     * @return
     */
    public List<TenantDTO> from(List<TenantDO> tenantDOS);

    /**
     * @param tenantDTOS
     * @return
     */
    public List<TenantDO> to(List<TenantDTO> tenantDTOS);
}
