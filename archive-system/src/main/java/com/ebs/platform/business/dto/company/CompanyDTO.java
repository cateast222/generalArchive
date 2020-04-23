package com.ebs.platform.business.dto.company;

import com.ebs.platform.business.domain.professionentity.Company;
import com.ebs.platform.core.query.DTOHelp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value ="单位信息表单")
@DTOHelp(sourceEntity = Company.class)
public class CompanyDTO {
    @ApiModelProperty(value = "单位编号",name = "id")
    private Integer id;
    @ApiModelProperty(value = "单位名称",name = "company_Name")
    private  String companyName;
    @ApiModelProperty(value = "单位类别代码",name = "type_code")
    private  String typeCode;
    @ApiModelProperty(value = "单位代码",name = "company_code")
    private  String companyCode;
    @ApiModelProperty(value = "联系人",name = "link_man")
    private  String linkMan;
    @ApiModelProperty(value = "联系电话",name = "link_phone")
    private  String linkPhone;
    @ApiModelProperty(value = "单位地址",name = "address")
    private  String address;
}
