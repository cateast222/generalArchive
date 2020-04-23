package com.ebs.platform.business.service;

import com.ebs.platform.business.domain.AppDictDO;
import com.ebs.platform.business.dto.*;
import com.ebs.platform.business.dto.auth.SetRoleDTO;
import com.ebs.platform.business.dto.settings.AppSettingsDTO;
import com.ebs.platform.business.dto.settings.AppSettingsEditValueDTO;
import com.ebs.platform.core.dto.AppDictDTO;
import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.dto.RequestParamDTO;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

/**
 * 应用服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:35
 */
public interface IAppService {
    /**
     * 保存应用
     * @param appDTO
     */
    String saveApp(AppDTO appDTO);

    /**
     * 删除应用
     * @param id
     */
    void deleteApp(String id);

    /**
     * 列出所有应用
     * @return
     */
    List<AppDTO> listApp();

    /**
     * 保存应用前台权限
     * @param appPowerDTO
     */
    void saveAppPower(AppPowerDTO appPowerDTO);

    /**
     * 删除应用前台权限
     * @param id
     */
    void deleteAppPower(String id);

    /**
     * 列出应用前台权限
     * @param appId
     * @return
     */
    List<AppPowerDTO> listAppPower(String appId);

    void sortAppPower(List<AppPowerSortDTO> list);

    /**
     * 保存应用规则
     * @param appRuleDTO
     */
    void saveAppRule(AppRuleDTO appRuleDTO);

    /**
     * 删除应用规则
     * @param id
     */
    void deleteAppRule(String id);

    /**
     * 列出应用规则
     * @param appId
     * @return
     */
    List<AppRuleDTO> listAppRule(String appId);

    /**
     * 保存应用扩展权限
     * @param appPowerExtDTO
     */
    void saveAppPowerExt(AppPowerExtDTO appPowerExtDTO);

    /**
     * 删除应用扩展权限
     * @param id
     */
    void deleteAppPowerExt(String id);

    /**
     * 列出应用扩展权限（地址）
     * @param appId
     * @return
     */
    List<AppPowerExtDTO> listAppPowerExt(String appId);

    /**
     * 保存应用角色
     * @param appRoleDTO
     */
    void saveAppRole(AppRoleDTO appRoleDTO);

    /**
     * 删除应用角色
     * @param id
     */
    void deleteAppRole(String id);

    /**
     * 根据角色获取权限列表
     * @param id
     * @return
     */
    List<AppPowerDTO> getRolePowers(String id);
    /**
     * 列出应用角色
     * @param appId
     * @return
     */
    List<AppRoleDTO> listAppRole(String appId);

    /**
     * 保存字典
     * @param appDictDTO
     */
    void saveDict(AppDictDTO appDictDTO);

    List<AppDictValueDTO> listAppDictValueByCode(String code);

    Optional<AppDictValueDTO> getAppDictValueByCode(String key, String value);

    /**
     * 删除字典
     * @param id
     */
    void deleteDict(String id);

    /**
     * 列出应用字典
     * @param appId
     * @return
     */
    List<AppDictDTO> listAppDict(String appId);

    @Cacheable(value = "DictValue", key = "#p0")
    String getDictValueName(String id);

    /**
     * 保存一个字典值
     * @param appDictValueDTO
     */
    void saveDictValue(AppDictValueDTO appDictValueDTO);

    /**
     * 删除一个字典值
     * @param id
     */
    void deleteDictValue(String id);

    /**
     * 获取字典的值列表
     * @param dictId
     * @return
     */
    List<AppDictValueDTO> listAppDictValue(String dictId);

    List<AppDictValueDTO> getAppDictValue(AppDictDO dict);

    ///系统配置
    AppSettingsDTO findSettingsByCode(String code);
    void saveSettings(AppSettingsDTO setting);
    void saveSettingsValue(AppSettingsEditValueDTO settingValue);


    ///系统配置

    /**
     * 设置角色的Web权限
     * @param setRoleDTO
     */
    void setRolePowers(SetRoleDTO setRoleDTO);

    /**
     * 设置角色的规则
     * @param setRoleDTO
     */
    void setRoleRules(SetRoleDTO setRoleDTO);

	List<AppDictValueDTO> getTypeDictValueByAppIdAndValues(RequestParamDTO requestParamDTO);

    //void toRedis();
}
