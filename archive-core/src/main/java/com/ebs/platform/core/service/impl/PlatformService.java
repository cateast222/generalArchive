package com.ebs.platform.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ebs.platform.core.conf.ModuleConfig;
import com.ebs.platform.core.dto.*;
import com.ebs.platform.core.exception.BusinessException;
import com.ebs.platform.core.old.UserContextDTO;
import com.ebs.platform.core.service.IPlatformService;
import com.ebs.platform.core.util.WebResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ebs.platform.core.util.HttpUtils.*;
/**
 * 平台接口
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:57
 */
@Service
public class PlatformService implements IPlatformService {

    @Autowired
    ModuleConfig config;

    //系统配置
    public AppSettingsDTO getSettingsByCode(String token,String code){
        AppSettingsDTO settings = null;
        Map<String,String> params = new HashMap<>(1);
        params.put("code",code);

        //String token = this.login("18674805900","123456");

        String par = JSON.toJSONString(params);

        WebResult result = sendPost(getUrl("/app/getSettings"),par, token);
        if (result.getData() != null) {
            settings = JSON.parseObject(result.getData().toString(), new TypeReference<AppSettingsDTO>(){});
        }
        return settings; 
    }

    @Override
    public AppDictValueDTO getDictValue(String token,String key, String value) {
        Map<String,String> reqMap = new HashMap<>(2);
        reqMap.put("key",key);
        reqMap.put("value",value);
        WebResult response = sendGet(getUrl("/app/getDictValue/"+key+"/"+value),null,token);
        if(response.getCode()==0){
            if(response.getData()!=null)
                return JSON.parseObject(response.getData().toString(),new TypeReference<AppDictValueDTO>(){});
            else
                return null;
        }else
            return null;
    }

    //系统配置

    private String getUrl(String url){
        return config.getServerUrl() + url;
    }

    @Override
    public String login(String username,String password) {
        Map<String, String> userMap = new HashMap<String, String>(2);
        userMap.put("username", username);
        userMap.put("password", password);
        String par = JSON.toJSONString(userMap);
        WebResult result = sendPost(getUrl("/user/login"), par, null);
        return result.getData().toString();
    }

    @Override
    public String changeAppByTenantId(String token, String tenantId) {
        WebResult result = sendPost(getUrl("/user/changeTenant"), tenantId, token);
        return result.getData().toString();
    }

    @Override
    public UserInfoDTO getUserInfo(String token) {
        UserInfoDTO userDTO = new UserInfoDTO();
        WebResult result = sendGet(getUrl("/user/getMyUserInfo"), null, token);
        if (result.getData() != null) {
            userDTO = JSON.parseObject(result.getData().toString(), new TypeReference<UserInfoDTO>(){});
        }
        return userDTO;
    }

    @Override
    public List<AppDTO> listApp(String token) {
        List<AppDTO> appDTOS = new ArrayList<>();
        WebResult result = sendGet(getUrl("/user/listMyTenants"), null, token);
        if (result.getData() != null) {
            appDTOS = JSON.parseObject(result.getData().toString(), new TypeReference<List<AppDTO>>(){});
        }
        return appDTOS;
    }

    @Override
    public List<AppDictValueDTO> listDictValueByDictCode(String token,String dictCode) {
        List<AppDictValueDTO> dictValueDTOS = new ArrayList<>();
        WebResult result = sendPost(getUrl("/app/listAppDictValueByCode"), dictCode, token);
        if (result.getData() != null) {
            dictValueDTOS = JSON.parseObject(result.getData().toString(), new TypeReference<List<AppDictValueDTO>>(){});
        }
        return dictValueDTOS;
    }

    @Override
    public List<EntPersonnelDTO> listEntPersonnelByEntId(String token, String entId) {
        List<EntPersonnelDTO> entPersonnelDTOS = new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("entId", entId);
        WebResult result = sendGet(getUrl("/ent/listPersonnelByEntId"), map, token);
        if (result.getData() != null) {
            entPersonnelDTOS = JSON.parseObject(result.getData().toString(), new TypeReference<List<EntPersonnelDTO>>(){});
        }
        return entPersonnelDTOS;
    }

    @Override
    public List<EntPersonnelDTO> listEntPersonnelByDeptId(String token, String deptId) {
        List<EntPersonnelDTO> entPersonnelDTOS = new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("deptId", deptId);
        WebResult result = sendGet(getUrl("/ent/listPersonnelByDeptId"), map, token);
        if (result.getData() != null) {
            entPersonnelDTOS = JSON.parseObject(result.getData().toString(), new TypeReference<List<EntPersonnelDTO>>(){});
        }
        return entPersonnelDTOS;
    }

    @Override
    public void sendMail(String token,MailDTO mailDTO) {
        String par = JSON.toJSONString(mailDTO);
        WebResult result = sendPost(getUrl("/mail/sendMail"), par, token);
    }


	public String getMyToken() {
        String token="";
		try {
            token=((UserContextDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getToken();
		}catch(Exception e){
			e.printStackTrace();
		}
		return token;
	}
    
	public String getPersonnelName(String planPersonnelId) {
		String personnelName=null;
		WebResult result=sendPost(getUrl("/user/getPersonnelName"),planPersonnelId ,getMyToken());
		if (result.getData()!=null) {
		    personnelName = result.getData().toString();
//			personnelName=JSON.parseObject(result.getData().toString(),new TypeReference<String>(){});
		}
		return personnelName;
	}

	public EntDeptDTO getDept(String deptId){
        WebResult result = sendPost(getUrl("/ent/listEntDeptById"),deptId,getMyToken());
        if(result.getData()!=null){
            return JSON.parseObject(result.getData().toString(),new TypeReference<EntDeptDTO>(){});
        }
        return null;
    }

    public EntDTO getEnterprise(String entId){
        WebResult result = sendPost(getUrl("/ent/listEntById"),entId,getMyToken());
        if(result.getData()!=null){
            return JSON.parseObject(result.getData().toString(),new TypeReference<EntDTO>(){});
        }
        return null;
    }

	@Override
	public List<EntPersonnelDTO> getPersonnelNameByUserIds( RequestParamDTO userIdsReq) {
		List<EntPersonnelDTO> ret=new ArrayList<>();
		WebResult result = sendPost(getUrl("/user/getPersonnelNameByUserIds"),JSON.toJSONString(userIdsReq),getMyToken());
        if(result.getData()!=null){
            ret=JSON.parseObject(result.getData().toString(),new TypeReference<List<EntPersonnelDTO>>(){});
            return ret;
        }
		return null;
	}
	
	@Override
	public String saveEntForPartner(EntDTO entDTO) {
		WebResult result = sendPost(getUrl("/ent/saveEntForPartner"),JSON.toJSONString(entDTO),getMyToken());
		if (result.getData()!=null) {
			// String ret=JSON.parseObject(result.getData().toString(),new TypeReference<String>(){});
			String ret=result.getData().toString();
			return ret;
		}else {
			return null;
		}
	}

	@Override
	public List<AppDictValueDTO> getTypeDictValueByAppIdAndValues(RequestParamDTO req) {
		
		WebResult result = sendPost(getUrl("/app/getTypeDictValueByAppIdAndValues"),JSON.toJSONString(req),getMyToken());
		
		if(result.getData()!=null){
			List<AppDictValueDTO> ret=JSON.parseObject(result.getData().toString(),new TypeReference<List<AppDictValueDTO>>(){});
            return ret;
        }
		return null;
	}

	
}
