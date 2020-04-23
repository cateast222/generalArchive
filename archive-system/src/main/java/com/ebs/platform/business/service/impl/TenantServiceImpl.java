package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.*;
import com.ebs.platform.business.domain.*;
import com.ebs.platform.business.dto.RoleByAddUserRequest;
import com.ebs.platform.business.dto.TenantAddRequest;
import com.ebs.platform.business.dto.TenantDTO;
import com.ebs.platform.business.dto.UserByAddRoleRequest;
import com.ebs.platform.business.mapper.TenantMapper;
import com.ebs.platform.business.service.ITenantService;
import com.ebs.platform.core.enums.TenantTypeEnum;
import com.ebs.platform.core.enums.UserTypeEnum;
import com.ebs.platform.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 租户服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 10:55
 */
@Service
public class TenantServiceImpl extends BaseServiceImpl implements ITenantService {

    @Autowired
    private ITenantDao tenantDao;

    @Autowired
    private IEntDao entDao;

    @Autowired
    private IAppDao appDao;

    @Autowired
    private IAppRoleDao appRoleDao;

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private IAppUserTenantRoleDao appUserTenantRoleDao;

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    @Transactional
    public String add(TenantAddRequest req) {
        Optional<EntDO> ent = entDao.findById(req.getEntId());
        if (!ent.isPresent())
            throw new BusinessException("指定的企业没有找到。");

        Optional<AppDO> app = appDao.findById(req.getAppId());
        if (!app.isPresent())
            throw new BusinessException("指定的租户没有找到。");

        TenantDO tenant = tenantDao.queryByEnterpriseAndApp(ent.get(), app.get());
        if (tenant != null) {
            if(tenant.getDeleted()==false) throw new BusinessException("同一企业不能同时对一个应用创建多个租户。");
        }

        AccountDO adminUser = new AccountDO();
        adminUser.setUserType(UserTypeEnum.TenantAdmin);
        adminUser.setUserName(req.getUserName());
        adminUser.setPassword("123456");
        accountDao.save(adminUser);

        tenant = new TenantDO();
        tenant.setStartDate(new Date());
        tenant.setEndDate(req.getEndDate());
        tenant.setTenantType(TenantTypeEnum.Enterprise);
        tenant.setAdminUser(adminUser);
        tenant.setApp(app.get());
        tenant.setEnterprise(ent.get());
        tenantDao.save(tenant);

        adminUser.setCurrentTenant(tenant);
        accountDao.save(adminUser);

        return adminUser.getPassword();
    }

    @Override
    @Transactional
    public void setUsersToRole(RoleByAddUserRequest req) {
        Optional<AppRoleDO> role = appRoleDao.findById(req.getRoleId());
        if(!role.isPresent()) throw new BusinessException("指定的角色没有找到。");

        TenantDO tenant = checkTenant(getUserContext().getTenantId(),true);
        List<AccountDO> users = accountDao.findAllById(req.getUserIds());
        for(AccountDO user: users){
            AppUserTenantRoleDO obj = new AppUserTenantRoleDO();
            obj.setUser(user);
            obj.setRole(role.get());
            obj.setTenant(tenant);
            appUserTenantRoleDao.save(obj);
        }
    }

    @Transactional
    @Override
    public void setRolesToUser(UserByAddRoleRequest req) {
        Optional<AccountDO> user = accountDao.findById(req.getUserId());
        if(!user.isPresent()) throw new BusinessException("指定的用户没有找到。");

        TenantDO tenant = checkTenant(getUserContext().getTenantId(),true);
        appUserTenantRoleDao.deleteByUser(user.get());

        List<AppRoleDO> roles = appRoleDao.findAllById(req.getRoels());
        for(AppRoleDO role : roles){
            AppUserTenantRoleDO obj = new AppUserTenantRoleDO();
            obj.setUser(user.get());
            obj.setRole(role);
            obj.setTenant(tenant);
            appUserTenantRoleDao.save(obj);
        }
    }

    @Deprecated
    public void saveTenant(TenantDTO tenantDTO) {
        TenantDO tenantDO = tenantMapper.to(tenantDTO);
        tenantDao.save(tenantDO);
    }

    @Override
    public void deleteTenant(String id) {
    	TenantDO tenantDO=tenantDao.findById(id).get();
    	tenantDO.setDeleted(true);
    	
        tenantDao.save(tenantDO);
    }

    @Override
    public List<TenantDTO> listTenant() {
        
        List<TenantDO> result = tenantDao.findAll((r, q, c) -> {
            Path<Boolean> isDelete = r.get("deleted");
            return c.equal(isDelete, false);
        });
        return tenantMapper.from(result);
    }

    @Override
    public List<TenantDTO> listTenantByAppId(String appId) {
        Optional<AppDO> app = appDao.findById(appId);
        if(!app.isPresent()) throw new BusinessException("指定的应用无效。");
        if(!app.get().getOwnerUser().getId().equals(getUserContext().getUserId())) throw new BusinessException("你不是 ["+app.get().getName()+"] 的管理员，无法执行此操作。");
        return tenantMapper.from(tenantDao.queryAllByAppAndDeletedOrderByEndDate(app.get(),false));
    }

    private TenantDO checkTenant(String tenantId,boolean isThrowException){

        Optional<TenantDO> tenant = tenantDao.findById(getUserContext().getTenantId());
        if(!tenant.isPresent()){
            if(isThrowException){
                throw new BusinessException("当前租户无效。");
            }else{
                return null;
            }
        }else if(tenant.get().getDeleted()){
            if(isThrowException)
                throw new BusinessException("指定的租定已注销，如要继续使用，请联系管理员恢复。");
            else
                return null;
        }else if(tenant.get().getEndDate().before(new Date())){
            if(isThrowException)
                throw new BusinessException("当前租户已过期，过期时间为" + tenant.get().getEndDate() + "，请续期。");
            else
                return null;
        }else{
            return tenant.get();
        }

    }
}
