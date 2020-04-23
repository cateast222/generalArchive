package com.ebs.platform.core.old;

import com.ebs.platform.core.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/4/27 9:54
 */
public class UserContextDTO {

    private String userId;
    private String userName;
    private UserTypeEnum userType;
    private String tenantId;
    private String tenantName;
    private String entId;
    private String entName;
    private String deptId;
    private String deptName;
    private String personnelId;
    private Date createdAt;
    private Date expireAt;

    private List<String> apiList ;

    public List<String> getApiList() {
        return apiList;
    }

    public void setApiList(List<String> apiList) {
        this.apiList = apiList;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getUserHeadeImage() {
		return userHeadeImage;
	}

	public void setUserHeadeImage(String userHeadeImage) {
		this.userHeadeImage = userHeadeImage;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	private String personnelName;
    private String appId;
    private String appName;
    private String appMainUrl;
    
    private String refreshToken;// 下面这部分是当时开发微信点灯活动写的
    private String userHeadeImage;// 微信中的用户头像啥的
    private String userToken;

    @JsonIgnore
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppMainUrl() {
        return appMainUrl;
    }

    public void setAppMainUrl(String appMainUrl) {
        this.appMainUrl = appMainUrl;
    }


    // 请求时解析 请求令牌，还原属性
    public static UserContextDTO parse(Claims obj){
        UserContextDTO userDto = new UserContextDTO();
        userDto.setUserId((String)obj.get("userId"));
        userDto.setTenantId((String)obj.get("tenantId"));
        userDto.setUserName((String)obj.get("userName"));
        userDto.setPersonnelId((String)obj.get("personnelId"));
        userDto.setPersonnelName((String)obj.get("personnelName"));
        userDto.setAppId((String)obj.get("appId"));
        userDto.setAppName((String)obj.get("appName"));
        userDto.setDeptId((String)obj.get("deptId"));
        userDto.setDeptName((String)obj.get("deptName"));
        userDto.setEntId((String)obj.get("entId"));
        userDto.setEntName((String)obj.get("entName"));
        userDto.setToken((String)obj.get("token"));
        userDto.setAppMainUrl((String)obj.get("appMainUrl"));

        userDto.setApiList((List<String>) obj.get("apiList"));


        //userDto.setExpireAt(new Date(obj.get("expireAt",Long.class)));
        //userDto.setCreatedAt(new Date(obj.get("createdAt",Long.class)));
        userDto.setUserHeadeImage((String)obj.get("userHeadeImage"));
        userDto.setUserToken((String)obj.get("userToken"));
        userDto.setRefreshToken((String)obj.get("refreshToken"));
        
        if(obj.get("entName")==null){
            userDto.setTenantName(obj.get("appName") + " - " + obj.get("personnelName"));
        }else{
            userDto.setTenantName(obj.get("appName") + " - " + obj.get("entName"));
        }
        if(obj.get("userType")!=null) {
            userDto.setUserType(UserTypeEnum.valueOf((String) obj.get("userType")));
        }
        return userDto;
    }

    // setClaims(UserContextDTO.toMap(user))   这是编织到 Claims 里面
    public static Map<String,Object> toMap(UserContextDTO obj){
        Map<String,Object> userMap=new HashMap<>();
        userMap.put("userId",obj.getUserId());
        userMap.put("tenantId",obj.getTenantId());
        userMap.put("tenantName",obj.getTenantName());
        userMap.put("userName",obj.getUserName());
        userMap.put("userType",obj.getUserType());
        userMap.put("personnelId",obj.getPersonnelId());
        userMap.put("personnelName",obj.getPersonnelName());
        userMap.put("appId",obj.getAppId());
        userMap.put("appName",obj.getAppName());
        userMap.put("deptId",obj.getDeptId());
        userMap.put("deptName",obj.getDeptName());
        userMap.put("entId",obj.getEntId());
        userMap.put("entName",obj.getEntName());
        userMap.put("token",obj.getToken());
        userMap.put("appMainUrl", obj.getAppMainUrl());

        userMap.put("apiList",obj.getApiList());



        userMap.put("userHeadeImage",obj.getUserHeadeImage());
        userMap.put("refreshToken",obj.getRefreshToken());
        userMap.put("userToken", obj.getUserToken());

        //userMap.put("createdAt", obj.getCreatedAt().getTime());
        //userMap.put("expireAt", obj.getExpireAt().getTime());

        return userMap;
    }

    @Override
    public String toString() {
        return "UserContextDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", tenantId='" + tenantId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", entId='" + entId + '\'' +
                ", entName='" + entName + '\'' +
                ", deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", personnelId='" + personnelId + '\'' +
                ", createdAt=" + createdAt +
                ", expireAt=" + expireAt +
                ", apiList=" + apiList +
                ", personnelName='" + personnelName + '\'' +
                ", appId='" + appId + '\'' +
                ", appName='" + appName + '\'' +
                ", appMainUrl='" + appMainUrl + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", userHeadeImage='" + userHeadeImage + '\'' +
                ", userToken='" + userToken + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
