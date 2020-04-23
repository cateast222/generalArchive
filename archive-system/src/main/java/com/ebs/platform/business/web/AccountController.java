package com.ebs.platform.business.web;

import com.ebs.platform.business.domain.AccountDO;
import com.ebs.platform.business.domain.AppUserTenantRoleDO;
import com.ebs.platform.business.dto.AccountDTO;
import com.ebs.platform.business.dto.AppPowerDTO;
import com.ebs.platform.business.dto.AppRoleDTO;
import com.ebs.platform.business.dto.user.LoginDTO;
import com.ebs.platform.business.dto.user.UserAddRequest;
import com.ebs.platform.business.mylog.MyLog;
import com.ebs.platform.business.service.IAccountService;
import com.ebs.platform.core.BaseController;
import com.ebs.platform.core.dto.EntPersonnelDTO;
import com.ebs.platform.core.dto.RequestParamDTO;
import com.ebs.platform.core.exception.BusinessException;
import com.ebs.platform.core.old.ChangePasswordDTO;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

//import javax.ws.rs.GET;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理控制器
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 8:56
 */
@Api(value = "用户管理2343434", description = "用户的登陆，上下文等")
@RestController
@RequestMapping
public class AccountController extends BaseController {

    @Autowired
    private IAccountService accountService;

    @MyLog("登陆")
    @ApiOperation(value = "登陆")
    @PostMapping("user/login")
    public WebResult login(@RequestBody LoginDTO loginDTO) throws IOException,ClassNotFoundException {
        //如密码为-，采用id登录,mes系统产线端使用
        if ("-".equals(loginDTO.getPassword())) {
            AccountDO accountDO = accountService.queryById(loginDTO.getUsername());
            if (accountDO == null) {
                throw new BusinessException("用户不存在或密码错误");
            }
            loginDTO.setUsername(accountDO.getUserName());
            loginDTO.setPassword(accountDO.getPassword());
        }
        String token = accountService.login(loginDTO);
        return WebUtils.success(token);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("user/getMyUserInfo")
    public WebResult getMyUserInfo() {
        return WebUtils.success(accountService.getMyUserInfo());
    }

    @MyLog("当前用户更改密码")
    @ApiOperation(value = "当前用户更改密码")
    @PostMapping("user/changePassword")
    public WebResult changeMyPassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        accountService.changeMyPassword(changePasswordDTO);
        return WebUtils.success();
    }

    @MyLog("切换当前应用")
    @ApiOperation(value = "切换当前应用")
    @PostMapping("user/changeApp")
    public WebResult<String> changeMyApp(@RequestBody String appId){
        return WebUtils.success(accountService.changeMyApp(appId));
    }

//    @ApiOperation(value = "添加新账号,用于注册")
//    @PostMapping("user/saveAccount")
//    public WebResult saveAccount(@RequestBody AccountDTO accountDTO) {
//        accountService.saveAccount(accountDTO);
//        return WebUtils.success();
//    }


    @ApiOperation(value = "获取当前用户的权限列表")
    @GetMapping("user/listMyAppPower")
    public WebResult<List<AppPowerDTO>> listMyAppPower() {
        return WebUtils.success(accountService.listMyAppPower());
    }

    @ApiOperation(value = "获取当前的菜单")
    @GetMapping("user/listMyMenu")
    public WebResult<List<AppPowerDTO>> listMyMenu(){
        return WebUtils.success(accountService.listMyMenu());
    }

    @ApiOperation(value = "获取当前用户的API规则")
    @GetMapping("user/listMyAppRule")
    public WebResult listMyAppRule() {
        return WebUtils.success(accountService.listMyAppRule());
    }

    @MyLog("当前用户切换租户，返回新的token")
    @ApiOperation(value = "当前用户切换租户，返回新的token")
    @PostMapping("user/changeTenant")
    public WebResult changeMyTenant(@RequestBody String tenantId) {
        String token = accountService.changeMyTenant(tenantId);
        return WebUtils.success(token);
    }

    @ApiOperation(value = "获取当前用户的租户列表")
    @GetMapping("user/listMyTenants")
    public WebResult listMyTenant() {
        return WebUtils.success(accountService.listMyTenant());
    }

    @ApiOperation(value = "根据字典编码获取字典值列表")
    @GetMapping("dict/values/{code}")
    public WebResult listAppDictValueByCode(@PathVariable String code) {
        return WebUtils.success(accountService.listAppDictValueByCode(code));
    }

    @MyLog("创建一个用户")
    @ApiOperation(value="创建一个用户")
    @PostMapping("user/add")
    public WebResult addUser(@RequestBody UserAddRequest req){
        accountService.addUser(req);
        return WebUtils.success();
    }

    @MyLog("删除一个用户")
    @ApiOperation(value="删除一个用户")
    @PostMapping("user/delete")
    public WebResult deleteUser(@RequestBody String id){
        accountService.deleteUser(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "根据当前用户类别查询下级用户列表")
    @GetMapping("user/list")
    public WebResult<AccountDTO> listUser(){
        return WebUtils.success(accountService.list());
    }

    @ApiOperation(value = "根据指定的用户查询角色")
    @PostMapping("user/listRoleByUser")
    public WebResult<List<AppRoleDTO>> listRoleByUserId(@RequestBody String userId){
        return WebUtils.success(accountService.listRoleByUserId(userId));
    }

    @ApiOperation(value = "根据指定的角色查询用户")
    @PostMapping("user/listUserByRole")
    public WebResult<List<AccountDTO>> listUserByRoleId(@RequestBody String roleId){
        return WebUtils.success(accountService.listUserByRoleId(roleId));
    }

    @ApiOperation(value = "根据personnelID查询personnnelName")
    @PostMapping("user/getPersonnelName")
    public WebResult getPersonnelName(@RequestBody String personnelId){
        return WebUtils.success(accountService.getPersonnelName(personnelId));
    }
    
    @ApiOperation(value = "根据userIDS查询personnnelName")
    @PostMapping("user/getPersonnelNameByUserIds")
    public WebResult<List<EntPersonnelDTO>> getPersonnelNameByUserIds(@RequestBody RequestParamDTO userIdsReq){
    	 
        return WebUtils.success(accountService.getPersonnelNameByUserIds(userIdsReq));
    }
    

    @Override
    protected WebResult queryMetadata() {
        return null;
    }

    @Override
    protected WebResult queryMetadataByName(String name) {
        return null;
    }
}
