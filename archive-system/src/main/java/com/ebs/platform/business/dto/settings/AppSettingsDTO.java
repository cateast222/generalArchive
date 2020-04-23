package com.ebs.platform.business.dto.settings;

import com.ebs.platform.business.domain.AppSettingValueDO;
import com.ebs.platform.core.enums.ScopeEnum;
import lombok.Data;

import java.util.List;

@Data
public class AppSettingsDTO {

    private String id;

//    private String appId;

    private ScopeEnum scopeEnum = ScopeEnum.User;

    private String name;

    private String code;

    private String typeDict;

    private String valueClassType;

    private String refTypeDict;

    /**
     * 在查询时按会给出该值，在新增和修改时AppSetting时不需要该值
     */
    private List<AppSettingsValueDTO> values;

}
