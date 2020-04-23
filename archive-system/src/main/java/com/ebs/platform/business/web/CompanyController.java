package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.company.CompanyDTO;
import com.ebs.platform.business.mylog.MyLog;
import com.ebs.platform.business.service.impl.CompanyServiceImpl;

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

@Api
@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    CompanyServiceImpl companyService;
    @MyLog("保存单位信息")
    @ApiOperation("单位信息")
    @PostMapping("/save")
    public WebResult save(@RequestBody CompanyDTO companyDTO){
        companyService.save(companyDTO);
        WebResult utils =  WebUtils.success();
        return utils ;
    }
    @MyLog("查询单位信息")
    @ApiOperation("保存单位信息")
    @PostMapping("/query")
    public WebResult query(@RequestBody Integer id){
        companyService.queryById(id);
        return WebUtils.success();
    }
    @MyLog("查询所有单位信息")
    @ApiOperation("查询所有单位信息")
    @PostMapping("/queryAll")
    public WebResult queryAll(){
        List<CompanyDTO> companyDTOS = companyService.queryAll();
        return WebUtils.success(companyDTOS);
    }
}
