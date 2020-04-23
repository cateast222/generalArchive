package com.ebs.platform.core.dto;

import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:08
 */
@ApiModel(value="企业对象")
public class EntDTO extends BaseEntity {
    @Override
    public String toString() {
        return "EntDTO{" +
                "name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
    @ApiModelProperty(value="企业名称",name="name",required=true)
    private String name;
    @ApiModelProperty(value="上级企业ID",name="parentId")
    private String parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
