package com.ebs.platform.business.mapper;

import com.ebs.platform.business.domain.AppSettingDO;
import com.ebs.platform.business.domain.AppSettingValueDO;
import com.ebs.platform.business.dto.settings.AppSettingsDTO;
import com.ebs.platform.business.dto.settings.AppSettingsValueDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-03T17:06:49+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class AppSettingsMapperImpl implements AppSettingsMapper {

    @Override
    public AppSettingsDTO toSettingsDTO(AppSettingDO settingDo) {
        if ( settingDo == null ) {
            return null;
        }

        AppSettingsDTO appSettingsDTO = new AppSettingsDTO();

        appSettingsDTO.setId( settingDo.getId() );
        appSettingsDTO.setScopeEnum( settingDo.getScopeEnum() );
        appSettingsDTO.setName( settingDo.getName() );
        appSettingsDTO.setCode( settingDo.getCode() );
        appSettingsDTO.setTypeDict( settingDo.getTypeDict() );
        appSettingsDTO.setValueClassType( settingDo.getValueClassType() );
        appSettingsDTO.setRefTypeDict( settingDo.getRefTypeDict() );

        return appSettingsDTO;
    }

    @Override
    public AppSettingDO toSettingsDO(AppSettingsDTO settingDTO) {
        if ( settingDTO == null ) {
            return null;
        }

        AppSettingDO appSettingDO = new AppSettingDO();

        appSettingDO.setScopeEnum( settingDTO.getScopeEnum() );
        appSettingDO.setId( settingDTO.getId() );
        appSettingDO.setName( settingDTO.getName() );
        appSettingDO.setCode( settingDTO.getCode() );
        appSettingDO.setTypeDict( settingDTO.getTypeDict() );
        appSettingDO.setValueClassType( settingDTO.getValueClassType() );
        appSettingDO.setRefTypeDict( settingDTO.getRefTypeDict() );

        return appSettingDO;
    }

    @Override
    public AppSettingsValueDTO toSettingsValueDTO(AppSettingValueDO settingValueDo) {
        if ( settingValueDo == null ) {
            return null;
        }

        AppSettingsValueDTO appSettingsValueDTO = new AppSettingsValueDTO();

        appSettingsValueDTO.setId( settingValueDo.getId() );
        appSettingsValueDTO.setValue( settingValueDo.getValue() );
        appSettingsValueDTO.setRefTypeDictValue( settingValueDo.getRefTypeDictValue() );

        return appSettingsValueDTO;
    }

    @Override
    public AppSettingValueDO toSettingsValueDo(AppSettingsValueDTO settingValueDTO) {
        if ( settingValueDTO == null ) {
            return null;
        }

        AppSettingValueDO appSettingValueDO = new AppSettingValueDO();

        appSettingValueDO.setId( settingValueDTO.getId() );
        appSettingValueDO.setValue( settingValueDTO.getValue() );
        appSettingValueDO.setRefTypeDictValue( settingValueDTO.getRefTypeDictValue() );

        return appSettingValueDO;
    }
}
