package com.ebs.platform.core.dto;

import com.ebs.platform.core.enums.ScopeEnum;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class AppSettingsDTO {

    private String id;

    private String appId;

    private ScopeEnum scopeEnum = ScopeEnum.User;

    private String name;

    private String code;

    private String typeDict;

    private String valueClassType;

    private String refTypeDict;

    private Set<AppSettingsValueDTO> settingValues = new LinkedHashSet<>();

}