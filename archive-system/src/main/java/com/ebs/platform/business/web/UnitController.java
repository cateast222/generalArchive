package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.archive.UnitDTO;
import com.ebs.platform.business.service.IUnitService;
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

/**
 * @author lwy
 * @Date 2019-05-29 10:51
 */
@Api(value = "单位管理",description = "对档案人员所属单位进行维护")
@RestController
@RequestMapping("unit")
public class UnitController {

    @Autowired
    private IUnitService iUnitService;

    @ApiOperation(value = "新增")
    @PostMapping("add")
    public WebResult<String> add(@RequestBody UnitDTO unitDTO){
        String id = iUnitService.add(unitDTO);
        return WebUtils.success(id);
    }

    @ApiOperation(value = "修改")
    @PostMapping("update")
    public WebResult update(@RequestBody UnitDTO unitDTO){
          iUnitService.update(unitDTO);
        return WebUtils.success();
    }

    @ApiOperation(value = "删除")
    @PostMapping("remove")
    public WebResult remove(@RequestBody String id){
        iUnitService.remove(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "根据主键查询")
    @PostMapping("queryById")
    public WebResult<UnitDTO> queryById(@RequestBody String id){
        UnitDTO unitDTO = iUnitService.queryById(id);
        return WebUtils.success(unitDTO);
    }

    @ApiOperation(value = "根据单位分类名查询")
    @PostMapping("findAll")
    public WebResult<List<UnitDTO>> findAll(@RequestBody(required = false) String className){
        List<UnitDTO> unitDTOS = iUnitService.findAll(className);
        return WebUtils.success(unitDTOS);
    }

    @ApiOperation(value = "根据条件查询")
    @PostMapping("queryByFilter")
    @MetadataFunction
    public WebResult<QueryResponse<List<UnitDTO>>> queryByFilter(@RequestBody QueryRequest<UnitDTO> req){
        QueryResponse<List<UnitDTO>> response = iUnitService.queryByFilter(req);
        return WebUtils.success(response);
    }
}
