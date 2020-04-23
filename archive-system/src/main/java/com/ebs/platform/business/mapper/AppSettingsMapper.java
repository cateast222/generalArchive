package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppSettingDO;
import com.ebs.platform.business.domain.AppSettingValueDO;
import com.ebs.platform.business.dto.settings.AppSettingsDTO;
import com.ebs.platform.business.dto.settings.AppSettingsValueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AppSettingsMapper {
    AppSettingsDTO toSettingsDTO(AppSettingDO settingDo);
    @Mapping(target = "scopeEnum",source = "scopeEnum")
    AppSettingDO toSettingsDO(AppSettingsDTO settingDTO);

    AppSettingsValueDTO toSettingsValueDTO(AppSettingValueDO settingValueDo);
    AppSettingValueDO toSettingsValueDo(AppSettingsValueDTO settingValueDTO);
}
