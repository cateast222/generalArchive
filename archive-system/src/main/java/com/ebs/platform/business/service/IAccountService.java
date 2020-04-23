package com.ebs.platform.business.service;

import com.ebs.platform.business.domain.AccountDO;
import com.ebs.platform.business.dto.AccountDTO;
import com.ebs.platform.business.dto.AppPowerDTO;
import com.ebs.platform.business.dto.AppRoleDTO;
import com.ebs.platform.business.dto.user.LoginDTO;
import com.ebs.platform.business.dto.user.UserAddRequest;
import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.dto.EntPersonnelDTO;
import com.ebs.platform.core.dto.RequestParamDTO;
import com.ebs.platform.core.old.ChangePasswordDTO;
import com.ebs.platform.core.old.UserContextDTO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 用户服务
 * 提供登陆,获取权限，角色等用户上下文相关接口
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 16:12
 */
public interface IAccountService {

    /**
     * 登陆方法，返回JWTToken
     * @param loginDTO
     * @return
     */
    String login(LoginDTO loginDTO) throws IOException,ClassNotFoundException;

    /**
     * 获取当前登陆用户上下文
     * @return
     */
    UserContextDTO getMyUserInfo();

    /**
     * 当前用户修改密码
     * @param changePasswordDTO
     */
    void changeMyPassword(ChangePasswordDTO changePasswordDTO);

    /**
     * 切换当前的App，只针对ApplicationAdmin
     * @param appId
     * @return
     */
    String changeMyApp(String appId);

    /**
     * 获取当前登陆用户当前应用的所有前台权限
     * @return
     */
    List<AppPowerDTO> listMyAppPower();

    /**
     * 获取当前用户的菜单结构
     * @return
     */
    List<AppPowerDTO> listMyMenu();
    /**
     * 获取当前登陆用户当前应用的所有规则
     * @return
     */
    List<Map> listMyAppRule();

    List<AppRoleDTO> listRoleByUserId(String id);
    List<AccountDTO> listUserByRoleId(String id);
    /**
     * 获取指定用户当前应用的所有规则
     * @return
     */
    List<Map> listAppRuleByAccountId(String accountId, String TenantId);

    /**
     * 当前用户切换租户，返回新的token
     * @param tenantId
     * @return
     */
    String changeMyTenant(String tenantId);

    /**
     * 列出当前用户已租赁的应用
     * @return
     */
    List<Map> listMyTenant();

    List<Map> getUserInfoByAccountId(String accountId);

    /**
     * 根据字典编码获取字典值列表
     * @param code
     * @return
     */
    List<AppDictValueDTO> listAppDictValueByCode(String code);

    /**
     * 创建账号
     * @param req
     */
    void addUser(UserAddRequest req);

    void deleteUser(String id);

    List<AccountDTO> list();

	String getPersonnelName(String personnelId);

	

	List<EntPersonnelDTO> getPersonnelNameByUserIds(RequestParamDTO userIdsReq);

	AccountDO queryById(String id);
}
