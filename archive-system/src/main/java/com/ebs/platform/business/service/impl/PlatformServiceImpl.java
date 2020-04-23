package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.*;
import com.ebs.platform.business.domain.*;
import com.ebs.platform.core.enums.TenantTypeEnum;
import com.ebs.platform.business.service.IPlatformService;
import com.ebs.platform.core.enums.SexEnum;
import com.ebs.platform.core.enums.UserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 管理平台服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 11:20
 */
@Service
public class PlatformServiceImpl extends BaseServiceImpl implements IPlatformService {
    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private IAppDao appDao;
    @Autowired
    private IAppPowerDao appPowerDao;
    @Autowired
    private IAppRuleDao appRuleDao;
    @Autowired
    private IEntDao entDao;
    @Autowired
    private ITenantDao tenantDao;
    @Autowired
    private IPersonnelDao personnelDao;
    @Autowired
    private IAppRoleDao appRoleDao;

    @Autowired
    private IAppUserTenantRoleDao appUserTenantRoleDao;

//    @Autowired
//    private IAppRolePowerDao appRolePowerDao;
//    @Autowired
//    private IAppRoleRuleDao appRoleRuleDao;
//    @Autowired
//    private ITenantAccountRoleDao tenantAccountRoleDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void firstInit() {

        //账户
        AccountDO accountDO = new AccountDO();
        accountDO.setUserName("platform");
        accountDO.setPassword("123456");
        accountDO.setUserType(UserTypeEnum.PlatformAdmin);
        accountDO.setCreatedDate(new Date());
        accountDO = accountDao.save(accountDO);

        //应用
        AppDO app = new AppDO();
        app.setName("管理平台");
        app.setOwnerUser(accountDO);
        appDao.save(app);

        //添加初始角色（平台管理员）
        AppRoleDO adminRole = new AppRoleDO();
        adminRole.setCode("platform_admin");
        adminRole.setName("平台管理员");
        adminRole.setApp(app);
        appRoleDao.save(adminRole);

        //添加初始企业
        EntDO entDO = new EntDO();
        entDO.setName("平台研发小组");
        entDao.save(entDO);

        //添加租户
        TenantDO tenantDO = new TenantDO();
        tenantDO.setStartDate(new Date());
        tenantDO.setEnterprise(entDO);
        tenantDO.setApp(app);
        tenantDO.setTenantType(TenantTypeEnum.Enterprise);
        tenantDO.setAdminUser(accountDO);
        tenantDao.save(tenantDO);

        //设置当前租户
        accountDO.setCurrentTenant(tenantDO);
        accountDao.save(accountDO);

        //添加人员，关联账号dev
        PersonnelDO personnelDO = new PersonnelDO();
        personnelDO.setUser(accountDO);
        personnelDO.setEnterprise(entDO);
        personnelDO.setSex(SexEnum.Male);
        personnelDO.setName("平台管理员");
        personnelDO.setTel("13755080944");
        personnelDao.save(personnelDO);

        //添加用户与角色的关系
        accountDO.setPersonnel(personnelDO);
        accountDao.save(accountDO);

        AppUserTenantRoleDO ref = new AppUserTenantRoleDO();
        ref.setRole(adminRole);
        ref.setTenant(tenantDO);
        ref.setUser(accountDO);
        appUserTenantRoleDao.save(ref);

    }

}
