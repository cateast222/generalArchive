package com.ebs.platform.business.dto;

import com.ebs.platform.core.enums.PowerTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 13:53
 */
@Data
@ApiModel(value="应用前台权限对象")
public class AppPowerDTO implements Comparable<AppPowerDTO>{

    private String id;

    @ApiModelProperty(value = "权限名称", name = "name", required = true)
    private String name;

    @ApiModelProperty(value = "权限编码", name = "code", required = true)
    private String code;

    @ApiModelProperty(value = "路由地址", name = "url")
    private String url;

    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

    @ApiModelProperty(value = "父级权限ID", name = "parentId")
    private String parentId;

    @ApiModelProperty(value = "Default(0,\"分类项\"), Menu(1,\"菜单\"),Button(2,\"按钮\"),Data(3,\"数据\"),Org(4,\"部门\"),Api(9,\"接口\")", required = true)
    private PowerTypeEnum powerType;

    @ApiModelProperty(value = "排序值")
    private Integer sort;

    private List<AppPowerDTO> childs;

    @Override
    public int compareTo(AppPowerDTO o) {
        if(this.getSort()>=o.getSort()){
            return 1;
        }
        return -1;
    }
}
