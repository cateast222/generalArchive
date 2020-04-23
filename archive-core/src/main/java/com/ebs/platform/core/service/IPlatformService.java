package com.ebs.platform.core.service;

import com.ebs.platform.core.dto.*;

import java.util.List;

/**
 * 平台API
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:00
 */
public interface IPlatformService {

    AppSettingsDTO getSettingsByCode(String token , String code);

    AppDictValueDTO getDictValue(String token,String key,String value);

    /**
     * 登陆并获取Token(jwt)
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);
    /**
     * 切换应用，并返回Token(jwt)
     * @param token
     * @param tenantId
     * @return
     */
    String changeAppByTenantId(String token, String tenantId);
    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    UserInfoDTO getUserInfo(String token);
    /**
     * 获取用户当前应用的所有模块
     * @param token
     * @return
     */
    //List<ModuleDTO> listModule(String token);
    /**
     * 获取用户当前应用的所有权限
     * @param token
     * @return
     */
    //List<PowerDTO> listPower(String token);
    /**
     * 获取用户当前应用的所有角色
     * @param token
     * @return
     */
    //List<RoleDTO> listRole(String token);
    /**
     * 获取当前用户可用的应用列表
     * @param token
     * @return
     */
    List<AppDTO> listApp(String token);
    /**
     * 根据字典编码获取字典值
     * @param dictCode
     * @return
     */
    List<AppDictValueDTO> listDictValueByDictCode(String token, String dictCode);
    /**
     * 获取企业下所有人员
     * @param token
     * @param entId
     * @return
     */
    List<EntPersonnelDTO> listEntPersonnelByEntId(String token, String entId);
    /**
     * 获取部门下所有人员
     * @param token
     * @param deptId
     * @return
     */
    List<EntPersonnelDTO> listEntPersonnelByDeptId(String token, String deptId);

    /**
     * 发送简单邮件
     * @param mailDTO
     */
    void sendMail(String token, MailDTO mailDTO);

    String getMyToken();
    
    /**
     * 	通过 userIDList获取
     * @param userIds
     * @return
     */
    List<EntPersonnelDTO> getPersonnelNameByUserIds(RequestParamDTO userIds);
    

    /**
     * 	保存往来单位并且 保存至企业表
     * @param entDTO
     * @return
     */
	String saveEntForPartner(EntDTO entDTO);
	
	/**
	 * 	通过字典值value获取 字典值表
	 * @param typeDictValue
	 * @return
	 */
	List<AppDictValueDTO> getTypeDictValueByAppIdAndValues(RequestParamDTO requestParamDTO);
}
