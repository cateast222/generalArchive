package com.ebs.platform.core.dto;

import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 14:02
 */
@ApiModel(value="字典项对象")
public class AppDictValueDTO extends BaseEntity {
    @Override
    public String toString() {
        return "AppDictValueDTO{" +
                "dictId='" + dictId + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
    @ApiModelProperty(value="所属字典ID",name="dictId",required=true)
    private String dictId;

    @ApiModelProperty(value="字典项名称",name="name",required=true)
    private String name;

//    @ApiModelProperty(value="字典项编码",name="code",required=true)
//    private String code;

    @ApiModelProperty(value="字典项值",name="value")
    private String value;

    @ApiModelProperty(value="字典项父级ID",name="parentId")
    private String parentId;

    @ApiModelProperty(value="备注",name="remark")
    private String remark;

    @ApiModelProperty(value="排序字段",name="sort")
    private String sort;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

    
}
