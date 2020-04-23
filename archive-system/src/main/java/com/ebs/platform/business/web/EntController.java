package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.*;
import com.ebs.platform.business.dto.personnel.PersonnelByEditRequest;
import com.ebs.platform.business.service.IEntService;
import com.ebs.platform.core.query.MetadataFunction;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 企业管理控制器
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/4 8:41
 */
@Api(value = "企业管理", description = "管理平台承载的企业信息")
@RestController
@RequestMapping("ent")
public class EntController {

    @Autowired
    private IEntService entService;

    @ApiOperation(value = "保存一个企业")
    @PostMapping("saveEnt")
    public WebResult saveEnt(@RequestBody EntDTO entDTO) {
        entService.saveEnt(entDTO);
        return WebUtils.success();
    }
    
    @ApiOperation(value = "保存一个企业为往来单位使用")
    @PostMapping("saveEntForPartner")
    public WebResult<String> saveEntForPartner(@RequestBody EntDTO entDTO) {
        
        return WebUtils.success(entService.saveEntForPartner(entDTO));
    }

    @ApiOperation(value = "删除一个企业")
    @GetMapping("del/{id}")
    public WebResult deleteEnt(@PathVariable String id) {
        entService.deleteEnt(id);
        return WebUtils.success();
    }

    //@MetadataFunction
    @ApiOperation(value = "列出所有企业")
    @PostMapping("list")
    public WebResult<QueryResponse<List<EntDTO>>> listEnt(@RequestBody QueryRequest req) {
        return WebUtils.success(entService.listEnt(req));
    }

    @ApiOperation(value = "保存一个部门")
    @PostMapping("saveEntDept")
    public WebResult saveEntDept(@RequestBody EntDeptByEditRequest entDeptDTO) {
        entService.saveDept(entDeptDTO);
        return WebUtils.success();
    }

    @ApiOperation(value = "删除一个部门")
    @PostMapping("dept/del/{id}")
    public WebResult deleteEntDept(@PathVariable String id) {
        entService.deleteEntDept(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "列出企业下所有部门")
    @GetMapping("dept/{entId}")
    public WebResult listEntDept(@PathVariable String entId) {
        return WebUtils.success(entService.listEntDept(entId));
    }

    @ApiOperation(value = "查询指定ID的部门信息")
    @PostMapping("listEntDeptById")
    public WebResult<EntDeptDTO> listEntDeptById(@RequestBody String deptId){
        return WebUtils.success(entService.listEntDeptById(deptId));
    }

    @ApiOperation(value = "查询指定ID的企业信息")
    @PostMapping("listEntById")
    public WebResult<EntDTO> listEntById(@RequestBody String entId){
        return WebUtils.success(entService.listEntById(entId));
    }

    @ApiOperation(value = "删除人员")
    @PostMapping("deletePersonnel")
    public WebResult deletePersonnel(@RequestBody String id) {
        entService.deletePersonnel(id);
        return WebUtils.success();
    }

    //@MetadataFunction
    @ApiOperation(value = "列出部门下所有人员")
    @PostMapping("personnel/listByDept")
    public WebResult<QueryResponse<List<PersonnelDTO>>> listPersonnelByDeptId(@RequestBody QueryRequest<String> req) {
        return WebUtils.success(entService.listPersonnelByDeptId(req));
    }

    //@MetadataFunction
    @ApiOperation(value = "模糊查询当前企业下的所有人员")
    @PostMapping("personnel/list")
    public WebResult<QueryResponse<List<PersonnelDTO>>> listPersonnelBySearch(@RequestBody QueryRequest req){
        return WebUtils.success(entService.listPersonnelBySearch(req));
    }

    @ApiOperation(value = "保存人员信息")
    @PostMapping("savePersonnel")
    public WebResult addPersonnel(@RequestBody PersonnelByEditRequest req){
        entService.savePersonnel(req);
        return WebUtils.success();
    }

    @ApiOperation(value = "根据部门ID获取所有账号")
    @PostMapping("listAccountByDept")
    public WebResult<List<Map>> listAccountByDept(@RequestBody String deptId){
        return WebUtils.success(entService.listAccountByDeptId(deptId));
    }

}
