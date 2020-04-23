package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.*;
import com.ebs.platform.business.dto.auth.SetRoleDTO;
import com.ebs.platform.business.dto.settings.AppSettingsDTO;
import com.ebs.platform.business.dto.settings.AppSettingsEditValueDTO;
import com.ebs.platform.business.dto.settings.AppSettingsValueDTO;
import com.ebs.platform.business.mylog.MyLog;
import com.ebs.platform.business.service.IAppService;
import com.ebs.platform.business.settings.SystemSetting;
import com.ebs.platform.core.dto.AppDictDTO;
import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.dto.RequestParamDTO;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用管理控制器
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 8:41
 */
@Api(value = "应用管理", description = "管理平台承载的应用")
@RestController
@RequestMapping("app")
public class AppController {

    @Autowired
    private IAppService appService;

    @ApiOperation(value = "字典放入Redis")
    @PostMapping("toRedis")
    public WebResult toRedis(){
        //appService.toRedis();
        return WebUtils.success();
    }


//    @Autowired
//    private SystemSetting sysSetting;

    @MyLog("对功能模块（权限）进行排序")
    @ApiOperation(value = "对功能模块（权限）进行排序")
    @PostMapping("power/sort")
    public WebResult sortAppPower(@RequestBody List<AppPowerSortDTO> list){
        appService.sortAppPower(list);
        return WebUtils.success();
    }

    @MyLog("保存一个应用")
    @ApiOperation(value = "保存一个应用")
    @PostMapping("saveApp")
    public WebResult<String> saveApp(@RequestBody AppDTO appDTO) {
        return WebUtils.success(appService.saveApp(appDTO));
    }

    @MyLog("物理删除一个应用")
    @ApiOperation(value = "物理删除一个应用")
    @PostMapping("deleteApp")
    public WebResult deleteApp(@RequestBody String appId) {
        appService.deleteApp(appId);
        return WebUtils.success();
    }

    @ApiOperation(value = "列出所有应用")
    @GetMapping("listApp")
    public WebResult listApp() {
        return WebUtils.success(appService.listApp());
    }

    @MyLog("保存应用前台权限")
    @ApiOperation(value = "保存应用前台权限")
    @PostMapping("saveAppPower")
    public WebResult saveAppPower(@RequestBody AppPowerDTO appPowerDTO) {
        appService.saveAppPower(appPowerDTO);
        return WebUtils.success("ok");
    }


    @MyLog("物理删除应用前台权限")
    @ApiOperation(value = "物理删除应用前台权限")
    @PostMapping("deleteAppPower")
    public WebResult deleteAppPower(@RequestBody String id) {
        appService.deleteAppPower(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "列出某应用前台权限")
    @PostMapping("listAppPower")
    public WebResult listAppPower(@RequestBody String appId) {
        return WebUtils.success(appService.listAppPower(appId));
    }

    @MyLog("保存应用规则")
    @ApiOperation(value = "保存应用规则")
    @PostMapping("saveAppRule")
    public WebResult saveAppRule(@RequestBody AppRuleDTO appRuleDTO) {
        appService.saveAppRule(appRuleDTO);
        return WebUtils.success();
    }

    @MyLog("删除某应用规则")
    @ApiOperation(value = "删除某应用规则")
    @PostMapping("deleteAppRule")
    public WebResult deleteAppRule(@RequestBody String id) {
        appService.deleteAppRule(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "列出某应用规则")
    @PostMapping("listAppRule")
    public WebResult listAppRule(@RequestBody String appId) {
        return WebUtils.success(appService.listAppRule(appId));
    }

    @MyLog("保存应用扩展权限")
    @ApiOperation(value = "保存应用扩展权限")
    @PostMapping("saveAppPowerExt")
    public WebResult saveAppPowerExt(@RequestBody AppPowerExtDTO appPowerExtDTO) {
        appService.saveAppPowerExt(appPowerExtDTO);
        return WebUtils.success();

    }

    @MyLog("删除应用扩展权限")
    @ApiOperation(value = "删除应用扩展权限")
    @PostMapping("deleteAppPowerExt")
    public WebResult deleteAppPowerExt(@RequestBody String id) {
        appService.deleteAppPowerExt(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "列出应用扩展权限（地址）")
    @PostMapping("listAppPowerExt")
    public WebResult listAppPowerExt(@RequestBody String appId) {
        return WebUtils.success(appService.listAppPowerExt(appId));
    }

    @MyLog("保存应用角色")
    @ApiOperation(value = "保存应用角色")
    @PostMapping("saveAppRole")
    public WebResult saveAppRole(@RequestBody AppRoleDTO appRoleDTO) {
        appService.saveAppRole(appRoleDTO);
        return WebUtils.success();
    }

    @MyLog("删除应用角色")
    @ApiOperation(value = "删除应用角色")
    @PostMapping("deleteAppRole")
    public WebResult deleteAppRole(@RequestBody String id) {
        appService.deleteAppRole(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "列出某应用角色")
    @PostMapping("listAppRole")
    public WebResult listAppRole(@RequestBody String appId) {
        return WebUtils.success(appService.listAppRole(appId));
    }

    @ApiOperation(value = "获取字典的 value 信息")
    @GetMapping("getDictValue/{key}/{value}")
    public WebResult<AppDictValueDTO> getDictValue(@PathVariable String key,@PathVariable  String value){
        return WebUtils.success(appService.getAppDictValueByCode(key,value));
    }

    @MyLog("根据配置KEY获取系统配置")
    @ApiOperation(value="根据配置KEY获取系统配置")
    @PostMapping("getSettings")
    public WebResult getSettings(@RequestBody String code){
        return WebUtils.success(appService.findSettingsByCode(code));
    }



    @MyLog("保存应用配置")
    @ApiOperation(value = "保存应用配置")
    @PostMapping("saveSettings")
    public WebResult saveSettings(@RequestBody AppSettingsDTO appSettingsDTO) {
        appService.saveSettings(appSettingsDTO);
        return WebUtils.success();
    }

    @MyLog("保存应用配置项")
    @ApiOperation(value = "保存应用配置项")
    @PostMapping("saveSettingsValue")
    public WebResult saveSettingsValue(@RequestBody AppSettingsEditValueDTO appSettingsValueDTO) {
        appService.saveSettingsValue(appSettingsValueDTO);
        return WebUtils.success();
    }

    @MyLog("保存应用字典")
    @ApiOperation(value = "保存应用字典")
    @PostMapping("saveDict")
    public WebResult saveDict(@RequestBody AppDictDTO appDictDTO) {
        appService.saveDict(appDictDTO);
        return WebUtils.success();
    }

    @MyLog("物理删除字典")
    @ApiOperation(value = "物理删除字典")
    @PostMapping("deleteDict")
    public WebResult deleteDict(@RequestBody String id) {
        appService.deleteDict(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "列出某应用字典")
    @PostMapping("listAppDict")
    public WebResult listAppDict(@RequestBody String appId) {
        return WebUtils.success(appService.listAppDict(appId));
    }

    @MyLog("保存一个字典值")
    @ApiOperation(value = "保存一个字典值")
    @PostMapping("saveDictValue")
    public WebResult saveDictValue(@RequestBody AppDictValueDTO appDictValueDTO) {
        appService.saveDictValue(appDictValueDTO);
        return WebUtils.success();
    }

    @MyLog("删除一个字典值")
    @ApiOperation(value = "删除一个字典值")
    @PostMapping("deleteDictValue")
    public WebResult deleteDictValue(@RequestBody String id) {
        appService.deleteDictValue(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "获取字典的值列表")
    @PostMapping("listAppDictValue")
    public WebResult<List<AppDictValueDTO>> listAppDictValue(@RequestBody String dictId) {
        return WebUtils.success(appService.listAppDictValue(dictId));
    }

    @ApiOperation(value = "获取字典的值列表")
    @PostMapping("listAppDictValueByCode")
    public WebResult<List<AppDictValueDTO>> listAppDictValueByCode(@RequestBody String code){
        return WebUtils.success(appService.listAppDictValueByCode(code));
    }

    @MyLog("设置角色的Web权限")
    @ApiOperation(value = "设置角色的Web权限")
    @PostMapping("setRolePowers")
    public WebResult setRolePowers(@RequestBody SetRoleDTO setRoleDTO) {
        appService.setRolePowers(setRoleDTO);
        return WebUtils.success();
    }

    @MyLog("设置角色的规则")
    @ApiOperation(value = "设置角色的规则")
    @PostMapping("setRoleRules")
    public WebResult setRoleRules(@RequestBody SetRoleDTO setRoleDTO) {
        appService.setRoleRules(setRoleDTO);
        return WebUtils.success();
    }


    @ApiOperation(value = "根据角色查询权限")
    @PostMapping("listAppPowerByRoleId")
    public WebResult<List<AppPowerDTO>> getRolePower(@RequestBody String roleId){
        return WebUtils.success(appService.getRolePowers(roleId));
    }
    
    @ApiOperation(value = "通过字典值的valueList查询字典值表")
    @PostMapping("getTypeDictValueByAppIdAndValues")
    public WebResult<List<AppDictValueDTO>> getTypeDictValueByAppIdAndValues(@RequestBody RequestParamDTO requestParamDTO){
        return WebUtils.success(appService.getTypeDictValueByAppIdAndValues(requestParamDTO));
    }
}
