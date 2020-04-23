package com.ebs.platform.core.conf;

import com.ebs.platform.core.util.PackageUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class ModuleApplicationRunner implements ApplicationRunner {

    @Autowired
    ModuleConfig config;

    @Override
    public void run(ApplicationArguments args) {

        PackageUtil.getEnums("");
    	

        //基本配置检查
        if(StringUtils.isEmpty(config.getServerUrl())){
            LoggerFactory.getLogger(this.getClass()).warn("没有配置 serverUrl 属性，contextController 将不能访问平台服务。");
        }
        if(StringUtils.isEmpty(config.getModuleName()) || StringUtils.isEmpty(config.getModuleDeveloperEmail()) || StringUtils.isEmpty(config.getModuleDesc())){
            LoggerFactory.getLogger(this.getClass()).warn("没有配置 module 属性，暂时没什么问题。");
        }
        if(config.getModuleDictionary().size()<=0){
            LoggerFactory.getLogger(this.getClass()).warn("没有配置 moduleDictionary 属性，所有数据字典需要预先配置，请确认当前模块是否没有使用字典功能。");
        }
    }
}
