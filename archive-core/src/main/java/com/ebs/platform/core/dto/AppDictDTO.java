package com.ebs.platform.core.dto;

import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.enums.ScopeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:01
 */
@ApiModel(value="字典对象")
public class AppDictDTO extends BaseEntity {
    @Override
    public String toString() {
        return "AppDictDTO{" +
                "appId='" + appId + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
    @ApiModelProperty(value="所属应用ID",name="appId",required=true)
    private String appId;
    @ApiModelProperty(value="字典名称",name="name",required=true)
    private String name;
    @ApiModelProperty(value="字典编码",name="code",required=true)
    private String code;
    @ApiModelProperty(value="备注",name="remake",required=false)
    private String remark;
    @ApiModelProperty(value="字典的定义范围，指明由哪一些来定义具体字典值",name="scope",required=false)
    private ScopeEnum scope;

    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ScopeEnum getScope() {
		return scope;
	}

	public void setScope(ScopeEnum scope) {
		this.scope = scope;
	}

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
