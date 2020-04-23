package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.RoleByAddUserRequest;
import com.ebs.platform.business.dto.TenantAddRequest;
import com.ebs.platform.business.dto.TenantDTO;
import com.ebs.platform.business.dto.UserByAddRoleRequest;

import java.util.List;

/**
 * 租户服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 15:56
 */
public interface ITenantService {

    /**
     * 根据企业创建一个租户
     * @param req
     * @return
     */
    String add(TenantAddRequest req);

    /**
     * 添加用户到指定的角色
     * @param req
     */
    void setUsersToRole(RoleByAddUserRequest req);

    /**
     * 添加角色到指定的用户
     * @param req
     */
    void setRolesToUser(UserByAddRoleRequest req);

    /**
     * 删除一个租户
     * @param id
     */
    void deleteTenant(String id);

    /**
     * 列出所有租户
     * @return
     */
    List<TenantDTO> listTenant();
    /**
     * 列出应用的所有租户
     * @param appId
     * @return
     */
    List<TenantDTO> listTenantByAppId(String appId);
}
