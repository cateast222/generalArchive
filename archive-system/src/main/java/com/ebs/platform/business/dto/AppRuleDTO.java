package com.ebs.platform.business.dto;

import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 13:59
 */
@ApiModel(value="应用的API规则")
public class AppRuleDTO extends BaseEntity {
    @Override
    public String toString() {
        return "AppRuleDTO{" +
                "appId='" + appId + '\'' +
                ", urlPattern='" + urlPattern + '\'' +
                ", methods='" + methods + '\'' +
                '}';
    }

    @ApiModelProperty(value="所属应用ID",name="appId",required=true)
    private String appId;
    @ApiModelProperty(value="url规则",name="urlPattern",required=true)
    private String urlPattern;
    @ApiModelProperty(value="允许的请求方式",name="methods")
    private String methods;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }
}
