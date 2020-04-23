package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.*;
import com.ebs.platform.business.domain.*;
import com.ebs.platform.business.dto.*;
import com.ebs.platform.business.dto.user.LoginDTO;
import com.ebs.platform.business.dto.user.UserAddRequest;
import com.ebs.platform.business.mapper.AccountMapper;
import com.ebs.platform.business.mapper.AppDictValueMapper;
import com.ebs.platform.business.mapper.AppPowerMapper;
import com.ebs.platform.business.mapper.AppRoleMapper;
import com.ebs.platform.business.service.IAccountService;
import com.ebs.platform.business.service.IAppService;
import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.dto.EntPersonnelDTO;
import com.ebs.platform.core.dto.RequestParamDTO;
import com.ebs.platform.core.enums.PowerTypeEnum;
import com.ebs.platform.core.enums.UserTypeEnum;
import com.ebs.platform.core.exception.BusinessException;
import com.ebs.platform.core.old.ChangePasswordDTO;
import com.ebs.platform.core.old.UserContextDTO;
import com.ebs.platform.core.util.ListConverterUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ebs.platform.core.enums.UserTypeEnum.*;

/**
 * 账户/用户上下文服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 9:08
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl implements IAccountService {

    private String secret = "foxzjwtcode";

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private IPersonnelDao personnelDao;

    @Autowired
    private IAppRoleDao appRoleDao;

    @Autowired
    private AppRoleMapper appRoleMapper;

    @Autowired
    private IAppDao appDao;

    @Autowired
    private IAppPowerDao appPowerDao;

    @Autowired
    private IEntDao entDao;

    @Autowired
    private AppPowerMapper appPowerMapper;

    @Autowired
    private IAppUserTenantRoleDao appUserTenantRoleDao;

    @Autowired
    private ITenantDao tenantDao;

    @Autowired
    private IAppDictDao appDictDao;

    @Autowired
    private IAppDictValueDao appDictValueDao;

    @Autowired
    private AppDictValueMapper appDictValueMapper;

    @Autowired
    private AccountMapper accountMapper;
    
//    @Autowired
//    private IPersonnelDao personnelDao;
    @Autowired
    private IAppService appService;

    @Override
    public List<Map> listAppRuleByAccountId(String accountId, String TenantId){
        String sql = "select d.id,d.url_pattern as urlPattern,d.methods from tb_user_tenant_role_ref a \n" +
                "join tb_app_role b on a.role_id = b.id\n" +
                "join tb_role_rule_relation c on b.id = c.role_id\n" +
                "join tb_app_rule d on c.rule_id = d.id\n" +
                "where a.user_id = ?\n" +
                "and a.tenant_id = ?\n" +
                "and b.is_deleted = 0 and d.is_deleted = 0";
        //列出当前账号的所有Api规则   老的表 tb_app_role_rule  ！！！！ 后面改成了 ：tb_role_rule_relation
        return sqlQuery(sql,new Object[]{accountId,TenantId});
    }

    @Override
    public String login(LoginDTO loginDTO) throws IOException ,ClassNotFoundException {

        AccountDO user = accountDao.queryTopByUserName(loginDTO.getUsername());

        if(user==null){
            throw new BusinessException("用户没有找到！");
        } else if (!user.getPassword().equals(loginDTO.getPassword())){
            throw new BusinessException("密码不正确!");
        }else if(user.getDeleted()){
            throw new BusinessException("该用户已经被禁用!");
        }



        return getToken(user);
    }

    private String getToken(UserContextDTO user){
        String token = Jwts.builder()
                .setSubject(user.getUserId())

                .setClaims(UserContextDTO.toMap(user))

                //60 * 60 * 24 * 1000 = 86400000 毫秒=一天过期
                //.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return token;
    }

    private String getToken(AccountDO obj){
        UserContextDTO user = new UserContextDTO();

        user.setUserId(obj.getId());
        user.setUserName(obj.getUserName());
        user.setUserType(obj.getUserType());


        // 根据用户找到角色 ———— 根据角色找到 后台rule(即API接口)
        List<Map> maps = null;

        if(obj.getCurrentTenant() != null){
            maps = this.listAppRuleByAccountId(user.getUserId(),obj.getCurrentTenant().getId());
        }

        List<AppRuleDO> ruleDOS =new ArrayList<>();
        try {
            AppRuleDO rule = new AppRuleDO();
            ruleDOS = ListConverterUtil.mapsToObjects(maps,rule.getClass());
        }catch (Exception e){

        }

        if(ruleDOS.size() != 0){

            List<String> apiList = ruleDOS.stream().map(AppRuleDO::getUrlPattern).collect(Collectors.toList());
            user.setApiList(apiList);
        }


        if(obj.getPersonnel()!=null){
            user.setPersonnelId(obj.getPersonnel().getId());
            user.setPersonnelName(obj.getPersonnel().getName());

            if(obj.getPersonnel().getDept()!=null){
                user.setDeptId(obj.getPersonnel().getDept().getId());
                user.setDeptName(obj.getPersonnel().getDept().getName());

                if(obj.getPersonnel().getEnterprise()!=null){
                    user.setEntId(obj.getPersonnel().getEnterprise().getId());
                    user.setEntName(obj.getPersonnel().getEnterprise().getName());
                }
            }
        }

        if(obj.getCurrentTenant()!=null){
            user.setTenantId(obj.getCurrentTenant().getId());
            user.setAppId(obj.getCurrentTenant().getApp().getId());
            user.setAppName(obj.getCurrentTenant().getApp().getName());
            user.setEntId(obj.getCurrentTenant().getEnterprise().getId());
            user.setEntName(obj.getCurrentTenant().getEnterprise().getName());
        }else{
            if(obj.getUserType()== ApplicationAdmin){
                List<AppDO> list = appDao.queryAppDOByDeletedAndAndOwnerUser(false,obj);
                Optional<AppDO> app = list.stream().findFirst();
                if(app.isPresent()){
                    user.setAppId(app.get().getId());
                    user.setAppName(app.get().getName());
                }
            }
            else if(obj.getUserType()==TenantAdmin){
                List<TenantDO> list = tenantDao.queryAllByAdminUserAndDeleted(obj,false);
                Optional<TenantDO> tenant = list.stream().findFirst();
                if(tenant.isPresent()){
                    user.setEntId(tenant.get().getEnterprise().getId());
                    user.setEntName(tenant.get().getEnterprise().getName());
                    user.setAppId(tenant.get().getApp().getId());
                    user.setAppName(tenant.get().getApp().getName());
                }
            }
        }
        return getToken(user);
    }


    @Override
    public UserContextDTO getMyUserInfo() {
        UserContextDTO userContextDTO = this.getUserContext();
        return userContextDTO;
    }

    @Override
    public void changeMyPassword(ChangePasswordDTO changePasswordDTO) {
        UserContextDTO userContextDTO = this.getUserContext();
        //AccountDO accountDO = accountDao.findOne(userContextDTO.getUserId());
        AccountDO accountDO = accountDao.getOne(userContextDTO.getUserId());
        if(accountDO.getPassword().equals(changePasswordDTO.getOldPassword())){
            accountDO.setPassword(changePasswordDTO.getNewPassword());
            accountDao.save(accountDO);
        }else{
            throw new BusinessException("原始密码不正确");
        }
    }

    @Override
    @Transactional
    public void addUser(UserAddRequest req){
        if (accountDao.existsByUserNameAndDeleted(req.getUserName(),false))
            throw new BusinessException("指定的用户名已存在。");

        AccountDO obj = new AccountDO();
        obj.setUserName(req.getUserName());
        obj.setPassword(req.getPassword());

        switch (getUserContext().getUserType()) {
            case PlatformAdmin:
                obj.setUserType(ApplicationAdmin);
                break;
            case ApplicationAdmin:
                obj.setUserType(TenantAdmin);
                break;
            case TenantAdmin:
                obj.setUserType(User);
                obj.setCurrentTenant(tenantDao.getOne(getUserContext().getTenantId()));
                break;
            case User:
                throw new BusinessException("只有 TenantAdmin 用户才能创建账号。");
            default:
                throw new BusinessException("无效的用户类型，不能执行此操作。");
        }

        if(!StringUtils.isEmpty(req.getPersonnelId())){
            Optional<PersonnelDO> personnel = personnelDao.findById(req.getPersonnelId());
            if(!personnel.isPresent())
                throw new BusinessException("指定的人员无效。");
            if(personnel.get().getUser()!=null && personnel.get().getUser().getDeleted()==false)
                throw new BusinessException("指定的人员已存在一个有效账号["+ personnel.get().getUser().getUserName()+"]。");

            obj.setPersonnel(personnel.get());
            accountDao.save(obj);

            personnel.get().setUser(obj);
            personnelDao.save(personnel.get());
        }else{
            accountDao.save(obj);
        }
    }

    @Override
    public void deleteUser(String id) {
        if(getUserContext().getUserType()!=UserTypeEnum.TenantAdmin)
            throw new BusinessException("你不是 ["+getUserContext().getEntName()+"] 的管理员，不能执行此操作。");

        Optional<AccountDO> user = accountDao.findById(id);
        if(!user.isPresent())
            throw new BusinessException("指定的用户没有找到。");

        if (user.get().getCurrentTenant()!=null){
            if(!user.get().getCurrentTenant().getEnterprise().getId().equals(getUserContext().getEntId()))
                throw new BusinessException("你跟目标用户没在同一个企业，不能执行此操作。");

            user.get().setUserName(user.get().getUserName() + "_del_" + (int)(1+Math.random()*(10-1+1)));
            user.get().setDeleted(true);
            accountDao.save(user.get());
        }
    }

    @Override
    public List<AccountDTO> list() {
        switch (getUserContext().getUserType()){
            case PlatformAdmin:
                return accountMapper.from(accountDao.queryAllByUserTypeAndDeleted(UserTypeEnum.ApplicationAdmin,false));
            case ApplicationAdmin:
                return accountMapper.from(accountDao.queryAllByUserTypeAndDeleted(UserTypeEnum.TenantAdmin,false));
            default:
                throw new BusinessException("暂不支持此类别用户的查询。");
        }
    }

    private List<AppPowerDTO> getApplicationAdminPower(String appId){
        Optional<AppDO> app = appDao.findById(appId);
        if(!app.isPresent()) throw new BusinessException("当前用户上下文指向了无效的应用。");
        if(app.get().getOwnerUser().getId().equals(appId)){
            return appPowerMapper.from(appPowerDao.queryAllByAppAndDeletedOrderBySortAsc(app.get(),false));
        }
        return null;
    }

    private List<AppPowerDO> getApplicationPowerByCurrUser(){
        Optional<AccountDO> user = accountDao.findById(getUserContext().getUserId());
        if(!user.isPresent()){
            throw new BusinessException("当前用户无效。");
        }
        if(user.get().getDeleted()){
            throw new BusinessException("指定的用户已被注销。");
        }
        if (StringUtils.isEmpty(getUserContext().getAppId())) throw new BusinessException("当前应用ID为空。");
        Optional<AppDO> app = appDao.findById(getUserContext().getAppId());
        if (!app.isPresent())
            throw new BusinessException("当前用户的应用无效。");

        Optional<TenantDO> tenant = tenantDao.findById(getUserContext().getTenantId());

        //List<AppPowerDTO> result = new ArrayList<>();
        List<AppPowerDO> result = new ArrayList<>();
        if(getUserContext().getUserType()== UserTypeEnum.ApplicationAdmin) {
            if (!app.get().getOwnerUser().getId().equals(getUserContext().getUserId()))
                throw new BusinessException("当前用户不是应用的所有者。");

            //result.addAll(appPowerMapper.from(appPowerDao.queryAllByAppAndDeletedAndPowerTypeInOrderBySortAsc(app.get(), false, new PowerTypeEnum[]{PowerTypeEnum.Menu, PowerTypeEnum.MenuItem})));
            result.addAll(appPowerDao.queryAllByAppAndDeletedAndPowerTypeInOrderBySortAsc(app.get(), false, new PowerTypeEnum[]{PowerTypeEnum.Menu, PowerTypeEnum.MenuItem}));
        }else if(getUserContext().getUserType()==TenantAdmin){
            if(!tenant.isPresent()){
                throw new BusinessException("当前租户无效。");
            }
            if(!tenant.get().getAdminUser().getId().equals(getUserContext().getUserId())){
                List<AppUserTenantRoleDO> list = appUserTenantRoleDao.queryAllByUserAndTenant(user.get(),tenant.get());
                for(AppUserTenantRoleDO obj : list){
                    //result.addAll(appPowerMapper.from( obj.getRole().getPowers()));
                    result.addAll(obj.getRole().getPowers().stream().filter(o->o.getDeleted()==false).collect(Collectors.toList()));
                }
            }else{
                //result = appPowerMapper.from(appPowerDao.queryAllByAppAndDeletedOrderBySortAsc(app.get(),false));
                result = appPowerDao.queryAllByAppAndDeletedOrderBySortAsc(app.get(),false);
            }
        }else if(getUserContext().getUserType()==User){
            List<AppUserTenantRoleDO> list = appUserTenantRoleDao.queryAllByUserAndTenant(user.get(),tenant.get());
            for(AppUserTenantRoleDO obj : list){
                //result.addAll(appPowerMapper.from( obj.getRole().getPowers()));
                result.addAll(obj.getRole().getPowers().stream().filter(o->o.getDeleted()==false).collect(Collectors.toList()));
            }
        }
        return result;
    }

    @Override
    public List<AppPowerDTO> listMyAppPower() {
        UserContextDTO userContextDTO = this.getUserContext();
        if(userContextDTO.getUserType()==ApplicationAdmin){
            Optional<AppDO> app = appDao.findById(userContextDTO.getAppId());
            if(!app.isPresent()) throw new BusinessException("当前用户上下文指向了无效的应用。");
            return getApplicationAdminPower(app.get().getId());
        }
        return appPowerMapper.from(getApplicationPowerByCurrUser());
    }

    @Override
    public List<AppPowerDTO> listMyMenu() {
        //获取当前所有权限
        List<AppPowerDO> source = this.getApplicationPowerByCurrUser();
        List<AppPowerDO> sourceNew = new ArrayList();
        for (AppPowerDO power : source){
            //获取权限的父级节点
            sourceNew.addAll(getPowerByParent(power));
        }
        //将父级权限节点合并到所有权限列表中
        source.addAll(sourceNew);

        Stream<AppPowerDO> rootMenu = source.stream().filter(n-> n.getParent()==null).distinct();
        List<AppPowerDTO> rootResult = appPowerMapper.from(rootMenu.collect(Collectors.toList()));
        for (AppPowerDTO root : rootResult){
            root.setChilds(getSubPower(root,appPowerMapper.from(source)));
        }
        return rootResult;
    }

    private List<AppPowerDO> getPowerByParent(AppPowerDO curr){
        List<AppPowerDO> result = new ArrayList();
        if(curr.getParent()!=null && curr.getParent().getDeleted()==false){
            result.add(curr.getParent());
            for (AppPowerDO power : getPowerByParent(curr.getParent())){
                if(power.getDeleted()==false) {
                    result.add(power);
                }
            }
        }
        return result;
    }

    /**
     * 获取父节点下的子菜单
     * @param root
     * @param source
     * @return
     */
    private List<AppPowerDTO> getSubPower(AppPowerDTO root,List<AppPowerDTO> source){
        Stream<AppPowerDTO> subs = source.stream().filter(n -> root.getId().equals(n.getParentId())).distinct();
        List<AppPowerDTO> result = subs.collect(Collectors.toList());
        for (AppPowerDTO menu : result) {
            menu.setChilds(getSubPower(menu,source) );
        }
        return result;
    }

    @Override
    public List<Map> listMyAppRule() {
        UserContextDTO userContextDTO = this.getUserContext();
        return this.listAppRuleByAccountId(userContextDTO.getUserId(),userContextDTO.getTenantId());
    }

    public List<AccountDTO> listUserByRoleId(String id){
        Optional<AppRoleDO> role = appRoleDao.findById(id);
        if(!role.isPresent()) throw new BusinessException("角色没有找到。");
        Optional<TenantDO> tenant = tenantDao.findById(getUserContext().getTenantId());
        if(!tenant.isPresent()) throw new BusinessException("当前租户无效。");

        List<AppUserTenantRoleDO> list = appUserTenantRoleDao.queryAllByRoleAndTenant(role.get(),tenant.get());
        List<AccountDTO> result = new ArrayList();
        for(AppUserTenantRoleDO ref : list){
            result.add(accountMapper.from(ref.getUser()));
        }
        return result;

    }

    @Override
    public List<AppRoleDTO> listRoleByUserId(String id) {
        Optional<AccountDO> user = accountDao.findById(id);
        if(!user.isPresent()) throw new BusinessException("用户没有找到。");
        if(user.get().getCurrentTenant()==null) throw new BusinessException("指定用户的当前租户为空。");

        List<AppUserTenantRoleDO> list = appUserTenantRoleDao.queryAllByUserAndTenant(user.get(),user.get().getCurrentTenant());
        List<AppRoleDTO> result = new ArrayList();
        for(AppUserTenantRoleDO ref : list){
            result.add(appRoleMapper.from(ref.getRole()));
        }
        return result;
    }

    @Override
    public String changeMyApp(String appId){
        UserContextDTO user = this.getUserContext();
        if(user.getUserType()!=ApplicationAdmin){
            throw new BusinessException("只有 [" + UserTypeEnum.ApplicationAdmin.getLabel() + "] 才能执行此操作。");
        }

        if(StringUtils.isEmpty(appId)){
            user.setAppId(null);
            user.setAppName(null);
        }else{
            Optional<AppDO> app = appDao.findById(appId);
            if(app.isPresent()){
                user.setAppId(app.get().getId());
                user.setAppName(app.get().getName());
            }else {
                user.setAppId(null);
                user.setAppName(null);
            }
        }
        return getToken(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String changeMyTenant(String tenantId) {
        UserContextDTO userContextDTO = this.getUserContext();
        AccountDO accountDO = accountDao.getOne(userContextDTO.getUserId());
        TenantDO tenantDO = tenantDao.getOne(tenantId);
        if(tenantDO == null || tenantDO.getDeleted()){
            throw new BusinessException("目标应用不存在");
        }else{
            if(tenantDO.getDeleted()){
                throw new BusinessException("指定的租户已注销");
            }
            TenantDO checkTenantDO =  tenantDao.getOne(tenantId);
            if(checkTenantDO == null){
                throw new BusinessException("您没有租赁目标应用");
            }else{
                // TODO: 2018-09-29 修改当前租户还未做处理
                //accountDO.setCurrentTenantId(tenantId);
                accountDao.save(accountDO);
                LoginDTO loginDTO = new LoginDTO();
                loginDTO.setUsername(accountDO.getUserName());
                loginDTO.setPassword(accountDO.getPassword());
                try {
                    return this.login(loginDTO);
                }catch(IOException ioEx){
                    throw new BusinessException("获取Api规则序列化出错");
                }catch(ClassNotFoundException cnfEx){
                    throw new BusinessException("获取Api规则序列化出错");
                }
            }
        }
    }

    @Override
    public List<Map> listMyTenant() {
        String sql = "select \n" +
                "c.id as tenantId,\n" +
                "c.start_date as startDate,\n" +
                "c.end_date as endDate,\n" +
                "d.name as appName,\n" +
                "d.id as appId \n" +
                " from tb_user a \n" +
                "left join tb_personnel b on a.id = b.user_id\n" +
                "left join tb_tenant c on b.ent_id = c.rent_id\n" +
                "left join tb_app d on c.app_id = d.id\n" +
                "where a.id = ?\n" +
                "and b.is_deleted = 0\n" +
                "and c.is_deleted = 0\n" +
                "and d.is_deleted = 0";
        UserContextDTO userContextDTO = this.getUserContext();
        return sqlQuery(sql,new Object[]{userContextDTO.getUserId()});
    }

    @Override
    public List<Map> getUserInfoByAccountId(String accountId) {
        String sql = "select \n" +
                "a.id as userId,\n" +
                "a.username,\n" +
                "a.user_type,\n" +
                "b.id as personnelId,\n" +
                "b.name as personnelName,\n" +
                "b.birth_date as birthDate,\n" +
                "b.email as personnelEmail,\n" +
                "b.sex as personnelSex,\n" +
                "b.tel as personnelTel,\n" +
                "c.name as entName,\n" +
                "c.id as entId,\n" +
                "d.name as deptName,\n" +
                "d.id as deptId,\n" +
                "e.id as tenantId,\n" +
                "e.tenant_type as tenantType,\n" +
                "f.name as appName,\n" +
                "f.id as appId\n" +
                " from tb_user a \n" +
                "left join tb_personnel b on a.id = b.user_id\n" +
                "left join tb_ent c on b.ent_id = c.id\n" +
                "left join tb_ent_dept d on b.dept_id = d.id\n" +
                "left join tb_tenant e on e.id = a.current_tenant_id\n" +
                "left join tb_app f on e.app_id = f.id\n" +
                "where a.id=?";
        return sqlQuery(sql,new Object[]{accountId});
    }

    @Override
    public List<AppDictValueDTO> listAppDictValueByCode(String code) {
        if(StringUtils.isEmpty(code)) throw new BusinessException("字典代码不能为空。");
        AppDictDO dict = appDictDao.queryByCode(code);
        if(dict==null) throw new BusinessException("指定的字典代码无效。");
        if(dict.getDeleted()) throw new BusinessException("指定的字典已删除。");
        return appService.getAppDictValue(dict);
    }

	@Override
	public String getPersonnelName(String personnelId) {
		PersonnelDO personnelDO=personnelDao.findById(personnelId).get();
		if (personnelDO==null) {
			throw new BusinessException("personnel 数据为空");
		}
		String personnelName=personnelDO.getName();
		return personnelName;
	}


	@Override
	public List<EntPersonnelDTO> getPersonnelNameByUserIds(RequestParamDTO userIdsReq) {

		List<PersonnelDO> personnelDOs=personnelDao.queryByDeletedAndUserIdIn(false,userIdsReq.getParamList());
		
		List<EntPersonnelDTO> entPersonnelDTOs=new ArrayList<EntPersonnelDTO>();
		for(PersonnelDO personnelDO:personnelDOs) {
			EntPersonnelDTO ent=new EntPersonnelDTO();
			
			ent.setUserId(personnelDO.getUser().getId());
			ent.setName(personnelDO.getName());
			entPersonnelDTOs.add(ent);
		}
		
		return entPersonnelDTOs;
	}

    @Override
    public AccountDO queryById(String id) {
        Optional<AccountDO> accountDO = accountDao.findById(id);
        if (accountDO.isPresent()) {
            return accountDO.get();
        } else {
            return null;
        }
    }

}
