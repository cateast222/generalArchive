package com.ebs.platform.core.controller;

import com.ebs.platform.core.conf.ModuleConfig;
import com.ebs.platform.core.dto.DictionaryObject;
import com.ebs.platform.core.metadata.EnumInfo;
import com.ebs.platform.core.metadata.FunctionInfo;
import com.ebs.platform.core.metadata.ServiceInfo;
import com.ebs.platform.core.old.UserDTO;
import com.ebs.platform.core.service.impl.PlatformService;
import com.ebs.platform.core.util.PackageUtil;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(value = "context",tags = {"这是一个新的属性"})
@RestController
@RequestMapping("context")
public class ContextController extends PlatformService {

    @Autowired
    private ModuleConfig config;

    @ApiOperation("模块信息")
    @RequestMapping(value = "info",method = {RequestMethod.GET})
    public WebResult<ModuleConfig> getConfig() {
        return WebUtils.success(config);
    }

    @ApiOperation("枚举定义 : IEnum")
    @RequestMapping(value = "enums",method = {RequestMethod.GET})
    public WebResult<Map<String, List<EnumInfo>>> queryEnums() {
        return WebUtils.success(PackageUtil.getEnums(""));
    }

    @ApiOperation("枚举定义 : IEnum")
    @RequestMapping(value = "enums/{enumTypeName}",method = {RequestMethod.GET})
    public WebResult<List<EnumInfo>> queryEnums(@PathVariable String enumTypeName){
        try {
            Class cls = Class.forName(enumTypeName);
            return WebUtils.success(PackageUtil.getEnums(cls));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation("服务定义 : @RestController")
    @RequestMapping(value = "services",method = {RequestMethod.GET})
    @GetMapping("services")
    public WebResult<List<ServiceInfo>> queryServices(){
        System.out.println(listDictValueByDictCode(getMyToken(),"cust.type"));
        return WebUtils.success(PackageUtil.getControllers(null));
    }

    @ApiOperation("字典定义")
    @GetMapping("dictions")
    public WebResult<List<DictionaryObject>> queryDictionarys(){
        return WebUtils.success(config.getModuleDictionary());
    }

    @ApiOperation("接口定义 : @RequestMapping,@GetMapping,@PostMapping...")
    @RequestMapping(value = "services",method = {RequestMethod.POST})
    public WebResult<FunctionInfo> queryFunctions(@RequestBody String functionName){

        List<ServiceInfo> serviceInfoList = PackageUtil.getControllers(null);
        System.out.println("packUtil的size是: "+serviceInfoList.size());
        for (ServiceInfo svc : serviceInfoList) {
            System.out.println("svc的Name: "+svc.name);
            if(svc.functions!=null && svc.functions.size()!=0){
                System.out.println("svc的functions 不为空的："+svc.functions.get(0).name);
                Optional<FunctionInfo> fun = svc.functions.stream().filter(n->n.getName().equals(functionName)).findFirst();
                if(fun.isPresent()) {
                    return WebUtils.success(fun.get());
                }
            }
        }
        return WebUtils.success();
    }


//    @ApiOperation("字典定义 : DictionaryDefine")
//    @RequestMapping(value = "dictionary",method = {RequestMethod.GET})
//    public WebResult<List<DictionaryObject>> queryDictionaryDefine(){
//        return WebUtils.success(config.getModuleDictionary());
//    }

    @ApiOperation(value = "登陆")
    @PostMapping("login")
    public WebResult login(@RequestBody UserDTO userDTO) {
        return WebUtils.success(login(userDTO.getUsername(), userDTO.getPassword()));
    }
}
