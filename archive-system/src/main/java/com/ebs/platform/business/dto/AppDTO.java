package com.ebs.platform.business.dto;

import com.ebs.platform.core.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 17:22
 */
@ApiModel(value="应用对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppDTO{

    @ApiModelProperty(value="应用名称",name="name",required=true)
    private String name;

    private String id;
}
