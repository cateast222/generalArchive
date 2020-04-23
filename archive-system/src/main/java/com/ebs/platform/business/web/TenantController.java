package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.RoleByAddUserRequest;
import com.ebs.platform.business.dto.TenantAddRequest;
import com.ebs.platform.business.dto.TenantDTO;
import com.ebs.platform.business.dto.UserByAddRoleRequest;
import com.ebs.platform.business.service.ITenantService;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 租户管理控制器
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 8:42
 */
@Api(value = "租户管理", description = "管理平台的租户")
@RestController
@RequestMapping("tenant")
public class TenantController {
    @Autowired
    private ITenantService tenantService;

    @ApiOperation(value = "创建一个租户")
    @PostMapping("add")
    public WebResult<String> add(@RequestBody TenantAddRequest req){
        return WebUtils.success(tenantService.add(req));
    }

    @ApiOperation(value = "注销一个租户")
    @PostMapping("cancel")
    public WebResult deleteTenant(@RequestBody String id) {
        tenantService.deleteTenant(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "列出所有租户")
    @PostMapping("list")
    public WebResult<List<TenantDTO>> listTenantByAppId(@RequestBody String appId) {
        return WebUtils.success(tenantService.listTenantByAppId(appId));
    }
    @ApiOperation(value = "根据角色设置用户")
    @PostMapping("setUsersToRole")
    public WebResult addUsersToRole(@RequestBody RoleByAddUserRequest req){
        tenantService.setUsersToRole(req);
        return WebUtils.success();
    }

    @ApiOperation(value = "根据用户设置角色")
    @PostMapping("setRolesToUser")
    public WebResult addRolesToUser(@RequestBody UserByAddRoleRequest req){
        tenantService.setRolesToUser(req);
        return WebUtils.success();
    }


}
