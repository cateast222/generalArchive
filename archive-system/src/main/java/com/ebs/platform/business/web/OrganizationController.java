package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.organization.OrganizationDTO;
import com.ebs.platform.business.dto.organization.OrganizationRequestDTO;
import com.ebs.platform.business.service.IOrganizationService;
import com.ebs.platform.core.query.MetadataFunction;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "机构设置" ,description = "机构设置")
@RestController
@RequestMapping("organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;
    @ApiOperation(value = "过滤查询机构信息")
    @MetadataFunction
    @PostMapping("/findByFilter")
    public WebResult<QueryResponse<List<OrganizationDTO>>> findByFilter(@RequestBody QueryRequest<OrganizationRequestDTO> request){
        QueryResponse<List<OrganizationDTO>> response = organizationService.findbyFilter(request);
        return WebUtils.success(response);
    }

    @PostMapping("/save")
    @ApiOperation("保存机构信息")
    public WebResult save(@RequestBody OrganizationDTO organizationDTO) {
        organizationService.save(organizationDTO);
        return WebUtils.success();
    }

    @PostMapping("/findIdAndName")
    @ApiOperation("查找所有机构信息的id和名称")
    public WebResult findNameAndId(){
        List<Map> idAndName = organizationService.findIdAndName();
        return WebUtils.success(idAndName);
    }

}
