package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.*;
import com.ebs.platform.business.domain.*;
import com.ebs.platform.business.dto.*;
import com.ebs.platform.business.dto.auth.SetRoleDTO;
import com.ebs.platform.business.dto.settings.AppSettingsDTO;
import com.ebs.platform.business.dto.settings.AppSettingsEditValueDTO;
import com.ebs.platform.business.dto.settings.AppSettingsValueDTO;
import com.ebs.platform.business.mapper.*;
import com.ebs.platform.business.service.IAppService;
import com.ebs.platform.core.dto.AppDictDTO;
import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.dto.RequestParamDTO;
import com.ebs.platform.core.enums.ScopeEnum;
import com.ebs.platform.core.enums.UserTypeEnum;
import com.ebs.platform.core.exception.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 应用服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 9:51
 */
@Service
public class AppServiceImpl extends BaseServiceImpl implements IAppService {


    @Autowired
    private IAppSettingsDao settingsDao;
    @Autowired
    private IAppSettingsValueDao settingsValueDao;
    @Autowired
    private AppSettingsMapper settingsMapper;


    @Autowired
    private IAppDao appDao;
    @Autowired
    private AppMapper appMapper;

    @Autowired
    private IAppPowerDao appPowerDao;

    @Autowired
    private AppPowerMapper appPowerMapper;

    @Autowired
    private IAppRuleDao appRuleDao;

    @Autowired
    private AppRuleMapper appRuleMapper;

    @Autowired
    private IAppPowerExtDao appPowerExtDao;

    @Autowired
    private AppPowerExtMapper appPowerExtMapper;

    @Autowired
    private IAppRoleDao appRoleDao;

    @Autowired
    private AppRoleMapper appRoleMapper;

    @Autowired
    private IAppDictDao appDictDao;

    @Autowired
    private AppDictMapper appDictMapper;

    @Autowired
    private IAppDictValueDao appDictValueDao;

    @Autowired
    private AppDictValueMapper appDictValueMapper;

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private ITenantDao tenantDao;

    @Override
    public String saveApp(AppDTO appDTO) {
        AppDO appDO = appMapper.to(appDTO);
        AccountDO accountDO = accountDao.findById(getUserContext().getUserId()).get();
        if (accountDO == null) {
            throw new BusinessException("用户为空");
        }
        appDO.setOwnerUser(accountDO);
        appDao.save(appDO);

        return appDO.getId();
    }

    @Override
    public void deleteApp(String id) {
        AppDO appDO = appDao.findById(id).get();
        appDO.setDeleted(true);
        appDao.save(appDO);
        //appDao.deleteById(id);
    }

    @Override
    public List<AppDTO> listApp() {
        if (getUserContext().getUserType() == UserTypeEnum.PlatformAdmin) {           //如果是平台管理员，返回所有
            return appMapper.from(appDao.queryAllByDeleted(false));
        } else if (getUserContext().getUserType() == UserTypeEnum.ApplicationAdmin) {  // 如果是应用管理员，所返自己的
            return appMapper.from(appDao.queryAppDOByDeletedAndAndOwnerUser(false, accountDao.getOne(getUserContext().getUserId())));
        } else {
            throw new BusinessException("你没有权限查询应用信息。");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAppPower(AppPowerDTO appPowerDTO) {
//        if(!(appPowerDTO.getPowerType() == PowerTypeEnum.Default && StringUtils.isEmpty(appPowerDTO.getParentId()))){
//            throw new BusinessException("主节点类型只能为 [" + PowerTypeEnum.Default.getLabel() + "]");
//        }
        checkApp(getUserContext().getAppId());
        AppPowerDO appPowerDO = appPowerMapper.to(appPowerDTO);
        appPowerDO.setApp(appDao.getOne(getUserContext().getAppId()));
        //appPowerDO.setSort(appPowerDao.maxSort(getUserContext().getAppId()).orElse(0));
        if(StringUtils.isEmpty(appPowerDao.maxSort(getUserContext().getAppId()))){
          appPowerDO.setSort(0);
        }else {
          appPowerDO.setSort(Integer.valueOf(appPowerDao.maxSort(getUserContext().getAppId())));
        }
        if(!StringUtils.isEmpty(appPowerDTO.getParentId())) {
            appPowerDO.setParent(appPowerDao.getOne(appPowerDTO.getParentId()));
        }

        appPowerDao.save(appPowerDO);
    }

    @Override
    public void deleteAppPower(String id) {
        AppPowerDO appPowerDO = appPowerDao.findById(id).orElse(null);
        if (appPowerDO == null) {
            throw new BusinessException("要删除的对象没有找到。");
        }
        checkApp(appPowerDO.getApp());

        appPowerDO.setDeleted(true);
        //appPowerDao.deleteById(id);
        appPowerDao.save(appPowerDO);
    }

    private AppDO checkApp(AppDO app) {
        if (app == null) {
            throw new BusinessException("无效的 AppId ");
        }
        if (app.getDeleted()) {
            throw new BusinessException("指定的应用已删除。");
        }
//        if (!app.getOwnerUser().getId().equals(getUserContext().getUserId())) {
//            throw new BusinessException("你不是当前应用的管理员，不能执行此操作。");
//        }
        return app;
    }

    private AppDO checkApp(String appId) {
        if (appId == null) {
            //throw new BusinessException("appId 不能为空。");
            appId=getUserContext().getAppId();
        }

        Optional<AppDO> app = appDao.findById(appId);
        if (!app.isPresent()) {
            throw new BusinessException("指定的 AppId 不存在。");
        }

        return checkApp(app.get());
    }

    @Override
    public List<AppPowerDTO> listAppPower(String appId) {
        return appPowerMapper.from(appPowerDao.queryAllByAppAndDeletedOrderBySortAsc(checkApp(appId), false));
    }

    @Override
    public void sortAppPower(List<AppPowerSortDTO> list) {
        List<String> ids = new ArrayList();
        for (AppPowerSortDTO obj : list) {
            ids.add(obj.getId());
        }

        List<AppPowerDO> objs = appPowerDao.findAllById(ids);
        for (AppPowerDO obj : objs) {
            for (AppPowerSortDTO source : list) {
                if (source.getId().equals(obj.getId())) {
                    obj.setSort(source.getSort());
                }
            }
        }
        appPowerDao.saveAll(objs);
    }

    @Override
    public void saveAppRule(AppRuleDTO appRuleDTO) {
        AppRuleDO appRuleDO = appRuleMapper.to(appRuleDTO);
        appRuleDO.setApp(appDao.findById(getUserContext().getAppId()).get());
        appRuleDao.save(appRuleDO);
    }

    @Override
    public void deleteAppRule(String id) {
        AppRuleDO appRuleDO = appRuleDao.findById(id).get();
        appRuleDO.setDeleted(true);

        appRuleDao.save(appRuleDO);
    }

    @Override
    public List<AppRuleDTO> listAppRule(String appId) {
        return appRuleMapper.from(appRuleDao.queryAllByAppAndAndDeleted(checkApp(appId), false));
    }

    @Override
    public void saveAppPowerExt(AppPowerExtDTO appPowerExtDTO) {
        AppPowerExtDO appPowerExtDO = appPowerExtMapper.to(appPowerExtDTO);
        appPowerExtDO.setApp(appDao.findById(getUserContext().getAppId()).get());
        appPowerExtDao.save(appPowerExtDO);
    }

    @Override
    public void deleteAppPowerExt(String id) {
        AppPowerExtDO appPowerExtDO = appPowerExtDao.findById(id).get();
        appPowerExtDO.setDeleted(true);
        appPowerExtDao.save(appPowerExtDO);
    }

    @Override
    public List<AppPowerExtDTO> listAppPowerExt(String appId) {
        return appPowerExtMapper.from(appPowerExtDao.queryByAppAndDeleted(checkApp(appId), false));
    }

    @Override
    public void saveAppRole(AppRoleDTO appRoleDTO) {
        // TODO: 2018-10-22 此处在update时会级联删除角色下的权限
        AppRoleDO appRoleDO;
        if (!StringUtils.isEmpty(appRoleDTO.getId())){
            appRoleDO = appRoleDao.getOne(appRoleDTO.getId());

            if(appRoleDO.getTenant()==null && getUserContext().getUserType()==UserTypeEnum.TenantAdmin || getUserContext().getUserType()==UserTypeEnum.User){
                throw new BusinessException("应用级角色，你不能修改。");
            }

            appRoleDO.setName(appRoleDTO.getName());
            appRoleDO.setCode(appRoleDTO.getCode());
            appRoleDO.setPowers(appRoleDO.getPowers());//多此一举
            appRoleDO.setRules(appRoleDO.getRules());//多此一举
        }else{
            appRoleDO = appRoleMapper.to(appRoleDTO);
            appRoleDO.setApp(appDao.getOne(getUserContext().getAppId()));
        }
        appRoleDao.save(appRoleDO);
    }

    @Override
    public void deleteAppRole(String id) {
        appRoleDao.deleteById(id);
    }

    @Override
    public List<AppRoleDTO> listAppRole(String appId) {
        return appRoleMapper.from(appRoleDao.queryAllByAppAndDeleted(checkApp(appId), false));
    }

    @Override
    public void saveDict(AppDictDTO appDictDTO) {
        //scope 是否需要前端传过来？ 因为已经给了默认值

        AppDictDO appDictDO = appDictMapper.to(appDictDTO);
        appDictDO.setApp(appDao.findById(getUserContext().getAppId()).get());
        appDictDao.save(appDictDO);
    }

    @Override
    @CacheEvict(value = "Dict", key = "#p0")
    public void deleteDict(String id) {
        AppDictDO appDictDO = appDictDao.findById(id).get();
        appDictDO.setDeleted(true);
        //appDictDao.deleteById(id);
        appDictDao.save(appDictDO);
    }

    @Override
    public List<AppDictDTO> listAppDict(String appId) {
        return appDictMapper.from(appDictDao.queryAllByAppAndAndDeleted(checkApp(appId), false));
    }

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void queryAllDictValue() {
        Cache cache = cacheManager.getCache("DictValue");
        List<AppDictValueDO> appDictValueDOList = appDictValueDao.findAll();
        appDictValueDOList.forEach(item -> {
            cache.put(item.getId(), item.getName());
        });
    }

    @Override
    @Cacheable(value = "DictValue", key = "#p0")
    public String getDictValueName(String id) {
        Optional<AppDictValueDO> appDictValueDO0 = appDictValueDao.findById(id);
        if (appDictValueDO0.isPresent()) {
            return appDictValueDO0.get().getName();
        }
        return "";
    }

    @Override
    public void saveDictValue(AppDictValueDTO appDictValueDTO) {
    	// 新增修改接口都是调用此处，所以需要判断
    	AppDictValueDO appDictValueDO;// = new AppDictValueDO();
    	if (!StringUtils.isEmpty(appDictValueDTO.getId())) {
    		appDictValueDO = appDictValueDao.queryByIdAndDeleted(appDictValueDTO.getId(),false);
    		
		}else {
			
			appDictValueDO = appDictValueMapper.to(appDictValueDTO);
		}
	    if (!StringUtils.isEmpty(appDictValueDTO.getParentId())) {
	            AppDictValueDO appDictValueDO2 = appDictValueDao.findById(appDictValueDTO.getParentId()).get();
	            appDictValueDO.setParent(appDictValueDO2);
	        }
	        // 根据scope 类型判断保存哪个值
	        AppDictDO appDictDO = appDictDao.findById(appDictValueDTO.getDictId()).get();
	        if (appDictDO.getScope() == ScopeEnum.User) {
	            AccountDO accountDO = accountDao.findById(getUserContext().getUserId()).get();
	            appDictValueDO.setUser(accountDO); //set user
	        }else if (appDictDO.getScope() == ScopeEnum.Tenant && getUserContext().getUserType()==UserTypeEnum.TenantAdmin) {
	            TenantDO tenantDO = tenantDao.findById(getUserContext().getTenantId()).get();
	            appDictValueDO.setTenant(tenantDO);// set tenant 
	        }
	        AppDO appDO = appDao.findById(getUserContext().getAppId()).get();
	        appDictValueDO.setApp(appDO);
	        appDictValueDO.setDict(appDictDO);
	        appDictValueDO.setName(appDictValueDTO.getName());
	        appDictValueDO.setValue(appDictValueDTO.getValue());
	        appDictValueDO.setRemark(appDictValueDTO.getRemark());
            if(!StringUtils.isEmpty(appDictValueDao.maxSort(appDictDO.getId()))){
                appDictValueDO.setSort(Integer.valueOf(appDictValueDao.maxSort(appDictDO.getId())));
            }else {
                appDictValueDO.setSort(0);
            }
	        //appDictValueDO.setSort(appDictValueDao.maxSort(appDictDO.getId()).orElse(0));

            saveDictCache(appDictValueDao.save(appDictValueDO));
    }

    @CachePut(value = "DictValue", key = "#p0.getId()")
    public String saveDictCache(AppDictValueDO appDictValueDO) {
        return appDictValueDO.getName();
    }

    @Override
    @CacheEvict(value = "DictValue", key = "#id")
    public void deleteDictValue(String id) {
        AppDictValueDO appDictValueDO = appDictValueDao.findById(id).get();
        appDictValueDO.setDeleted(true);
        appDictValueDao.save(appDictValueDO);
    }

    @Override
    @Cacheable(value = "Dict", key = "#dictId")
    public List<AppDictValueDTO> listAppDictValue(String dictId) {
        Optional<AppDictDO> dict = appDictDao.findById(dictId);
        if (dict.isPresent()) {
            return getAppDictValue(dict.get());
        } else {
            return null;
        }
    }

    @Override
    public AppSettingsDTO findSettingsByCode(String code) {
        AppSettingDO setting = settingsDao.queryTopByCode(code);
        AppSettingsDTO result = settingsMapper.toSettingsDTO(setting);

        List<AppSettingsValueDTO> list = new ArrayList<>();
        List<AppSettingValueDO> filterList = new ArrayList<>();
        switch (setting.getScopeEnum()){
            case Application:
                filterList = setting.getAppSettingValue().stream().filter((x->x.getApp().getId().equals(getUserContext().getAppId()))).collect(Collectors.toList());
                break;
            case Tenant:
                filterList = setting.getAppSettingValue().stream().filter((x->x.getTenant().getId().equals(getUserContext().getAppId()))).collect(Collectors.toList());
                break;
            case User:
                filterList = setting.getAppSettingValue().stream().filter(x->x.getUser().getId().equals(getUserContext().getUserId())).collect(Collectors.toList());
                break;
        }
        for(AppSettingValueDO item : filterList){
            AppSettingsValueDTO obj = new AppSettingsValueDTO();
            obj.setId(item.getId());
            obj.setValue(item.getValue());
            obj.setRefTypeDictValue(item.getRefTypeDictValue());
            list.add(obj);
        }
        result.setValues(list);
        return result;
    }

    @Override
    public void saveSettings(AppSettingsDTO setting) {
        AppSettingDO appSettingDO;
        if(StringUtils.isEmpty(setting.getId())){
            appSettingDO = new AppSettingDO();
            appSettingDO.setApp(appDao.findById(getUserContext().getAppId()).get());
        }else{
            appSettingDO = settingsDao.findById(setting.getId()).get();
        }
        appSettingDO.setCode(setting.getCode());
        appSettingDO.setName(setting.getName());
        appSettingDO.setScopeEnum(setting.getScopeEnum());
        appSettingDO.setTypeDict(setting.getTypeDict());
        appSettingDO.setRefTypeDict(setting.getRefTypeDict());
        appSettingDO.setValueClassType(setting.getValueClassType());
        appSettingDO.setDeleted(false);
        settingsDao.save(appSettingDO);
    }

    @Override
    public void saveSettingsValue(AppSettingsEditValueDTO settingValue) {
        AppSettingValueDO appSettingValueDO;
        if(StringUtils.isEmpty(settingValue.getId())){
            appSettingValueDO = new AppSettingValueDO();
        }else{
            appSettingValueDO = settingsValueDao.findById(settingValue.getId()).get();
        }
        appSettingValueDO.setAppSetting(settingsDao.findById(settingValue.getSettingId()).get());
        appSettingValueDO.setTenant(tenantDao.findById(getUserContext().getTenantId()).get());
        appSettingValueDO.setUser(accountDao.findById(getUserContext().getUserId()).get());
        appSettingValueDO.setValue(settingValue.getValue());
        appSettingValueDO.setRefTypeDictValue(settingValue.getRefTypeDictValue());
        appSettingValueDO.setDeleted(false);
        settingsValueDao.save(appSettingValueDO);
    }

    @Override
    public List<AppDictValueDTO> listAppDictValueByCode(String code) {
    	// 不同的 app 可能code 是相同的,新增的时候验证  code 在同一app里面不能重复
    	AppDO appDO =appDao.queryByIdAndDeleted(getUserContext().getAppId(), false);
        AppDictDO dict = appDictDao.queryByCodeAndAppAndDeleted(code,appDO,false);
        return getAppDictValue(dict);
    }

    @Override
    public List<AppDictValueDTO> getAppDictValue(AppDictDO dict){
        List<AppDictValueDTO> dictValueDTOs = null;
        if (dict != null) {
            checkApp(dict.getApp());

            if(dict.getScope()==ScopeEnum.Application){
                dictValueDTOs = appDictValueMapper.from(appDictValueDao.queryByAppAndDeleted(dict.getApp(),false));
            }else if(dict.getScope()==ScopeEnum.Tenant) {
//                TenantDO tenantDO2 = tenantDao.getOne(getUserContext().getTenantId());// 此方法不好用，断点调试此处对象的属性都是null,但是下面getID却有数据
//                String ss = tenantDO2.getId();
            	TenantDO tenantDO = tenantDao.queryByIdAndDeleted(getUserContext().getTenantId(),false);
                dictValueDTOs = appDictValueMapper.from(appDictValueDao.queryByTenantAndDictAndDeleted(tenantDO, dict, false));
            }else if(dict.getScope()==ScopeEnum.User){
                AccountDO user = accountDao.getOne(getUserContext().getUserId());
                dictValueDTOs = appDictValueMapper.from(appDictValueDao.queryByCreatedByAndDictAndDeleted(user,dict,false));
            }
        }
        return  dictValueDTOs;
    }

    @Override
    public Optional<AppDictValueDTO> getAppDictValueByCode(String key, String value) {
        List<AppDictValueDTO> values = listAppDictValueByCode(key);
        if(values==null) return Optional.empty();
        return values.stream().filter(x->x.getValue().equals(value)).findFirst();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setRolePowers(SetRoleDTO setRoleDTO) {
        AppRoleDO role = appRoleDao.getOne(setRoleDTO.getRoleId());
        List<AppPowerDO> powers = new ArrayList();
        for (String powerId : setRoleDTO.getCodes()) {
            powers.add(appPowerDao.getOne(powerId));
        }
        role.setPowers(powers);
        appRoleDao.save(role);
    }

    public List<AppPowerDTO> getRolePowers(String id){
        AppRoleDO role = appRoleDao.getOne(id);
        return appPowerMapper.from(role.getPowers());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setRoleRules(SetRoleDTO setRoleDTO) {
        // 删除：
        appRoleDao.deleteRoleAndRule(setRoleDTO.getRoleId());
        // 再插入：
        for (String ruleId : setRoleDTO.getCodes()) {
            appRoleDao.insertRoleAndRule(setRoleDTO.getRoleId(), ruleId);
        }
    }

	@Override // 通过 字典项 表的 value 查询 字典项
	public List<AppDictValueDTO> getTypeDictValueByAppIdAndValues(RequestParamDTO requestParamDTO) {
		List<String> typeDictValueList = requestParamDTO.getParamList();
		AppDO appDO =appDao.queryByIdAndDeleted(getUserContext().getAppId(), false);
		List<AppDictValueDO> dictValueDOs = appDictValueDao.queryByAppAndDeletedAndValueIn(appDO, false,typeDictValueList);
		// 手动转换 ，提高效率 ： 使用mapper会把其他的不要的也加载，速度会很慢
		List<AppDictValueDTO> appDictValueDTOs = new ArrayList<>();
		for(AppDictValueDO dictValueDO : dictValueDOs) {
			AppDictValueDTO appDictValueDTO = new AppDictValueDTO();
			appDictValueDTO.setId(dictValueDO.getId());
			appDictValueDTO.setDictId(dictValueDO.getDict().getId());
			appDictValueDTO.setName(dictValueDO.getName());
			appDictValueDTO.setValue(dictValueDO.getValue());
			appDictValueDTOs.add(appDictValueDTO);
		}
		return appDictValueDTOs;
	}

	//@Autowired
	//private StringRedisTemplate stringRedisTemplate;

//    @Override
//    public void toRedis() {
//        List<AppDictValueDO> dictValueDOS = appDictValueDao.queryByDeleted(false);
//        for(AppDictValueDO valueDO :dictValueDOS){
//            stringRedisTemplate.opsForValue().set(valueDO.getId(),valueDO.getName());
//        }
//    }
}
