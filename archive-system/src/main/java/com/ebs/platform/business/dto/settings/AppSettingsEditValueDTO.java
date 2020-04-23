package com.ebs.platform.business.dto.settings;


import lombok.Data;

@Data
public class AppSettingsEditValueDTO extends AppSettingsValueDTO {

    private String id;

    private String settingId;
}
