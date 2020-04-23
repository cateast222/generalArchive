package com.ebs.platform.core.conf;

import com.ebs.platform.core.dto.DictionaryObject;
import com.ebs.platform.core.enums.ScopeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "com.ebs")
public class ModuleConfig{

    /**
     * 提供系统认证服务，及权限、菜单、角色等核心服务的地址
     */
    private String serverUrl;

    /**
     * 当前服务模块的名称
     */
    private String moduleName;

    /**
     * 当前服务模块的描述
     */
    private String moduleDesc;

    /**
     * 模块的开发者邮箱
     */
    private String moduleDeveloperEmail;

    private List<DictionaryObject> moduleDictionary = new ArrayList<>();

}
