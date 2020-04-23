package com.ebs.platform.business.dto;

import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:07
 */
@ApiModel(value="部门对象")
public class EntDeptDTO extends BaseEntity {
    @Override
    public String toString() {
        return "EntDeptDTO{" +
                "entId='" + entId + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
    @ApiModelProperty(value="所属企业ID",name="entId",required=true)
    private String entId;
    @ApiModelProperty(value="部门名称",name="name",required=true)
    private String name;
    @ApiModelProperty(value="上级部门ID",name="parentId")
    private String parentId;

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

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
