package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppRuleDO;
import com.ebs.platform.business.dto.AppRuleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 17:23
 */
@Mapper(componentModel = "spring")
public interface AppRuleMapper {
    /**
     * @param appRuleDO
     * @return
     */
    @Mappings({
            @Mapping(source = "app.id",target = "appId")
    })
    public AppRuleDTO from(AppRuleDO appRuleDO);

    /**
     * @param appRuleDTO
     * @return
     */
    public AppRuleDO to(AppRuleDTO appRuleDTO);

    /**
     * @param appRuleDOS
     * @return
     */
    public List<AppRuleDTO> from(List<AppRuleDO> appRuleDOS);

    /**
     * @param appRuleDTOS
     * @return
     */
    public List<AppRuleDO> to(List<AppRuleDTO> appRuleDTOS);
}
