package com.ebs.platform.business.dto;

import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 外部扩展权限
 * 平台通过外部权限的地址，获取到权限列表，并提供统一授权支持
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:03
 */
@ApiModel(value="应用前台扩展权限对象")
public class AppPowerExtDTO extends BaseEntity {
    @Override
    public String toString() {
        return "AppPowerExtDTO{" +
                "appId='" + appId + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
    @ApiModelProperty(value="所属应用ID",name="appId",required=true)
    private String appId;
    @ApiModelProperty(value="扩展权限名称",name="name",required=true)
    private String name;
    @ApiModelProperty(value="扩展权限来源地址",name="url",required=true)
    private String url;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
