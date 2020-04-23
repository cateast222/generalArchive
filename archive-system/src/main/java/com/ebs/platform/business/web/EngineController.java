package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.engine.EngineDTO;
import com.ebs.platform.business.dto.engine.EngineDataDTO;
import com.ebs.platform.business.dto.engine.EngineQueryDTO;
import com.ebs.platform.business.service.IEngineService;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 引擎web接口
 * @author 496382223@qq.com 白の狐狸
 * @date 2019/1/03 9:53
 */
@Api(value = "引擎管理", description = "渲染引擎的相关逻辑")
@RestController
@RequestMapping("engine")
public class EngineController {

    @Autowired
    private IEngineService engineService;

    @ApiOperation(value = "执行Sql获取数据结果")
    @PostMapping("getSourceData")
    @ResponseBody
    public WebResult getSourceData(@RequestBody EngineDataDTO engineDataDTO){
        return WebUtils.success(engineService.getSourceData(engineDataDTO));
    }

    @ApiOperation(value = "根据id获取一个渲染定义")
    @PostMapping("getEngineById")
    public WebResult getEngineById(@RequestBody String id){
        return WebUtils.success(engineService.getEngineById(id));
    }
    @ApiOperation(value = "根据code获取一个渲染定义")
    @PostMapping("getEngineByCode")
    public WebResult getEngineByCode(@RequestBody String code){
        return WebUtils.success(engineService.getEngineByCode(code));
    }
    @ApiOperation(value = "保存一个渲染定义")
    @PostMapping("saveEngine")
    public WebResult saveEngine(@RequestBody EngineDTO engineDTO){
        engineService.saveEngine(engineDTO);
        return WebUtils.success();
    }
    @ApiOperation(value = "删除一个渲染定义")
    @PostMapping("removeEngine")
    public WebResult removeEngine(@RequestBody String id){
        engineService.removeEngine(id);
        return WebUtils.success();
    }
    @ApiOperation(value = "根据AppId查询所有渲染定义")
    @PostMapping("listEnginesByAppId")
    public WebResult listEnginesByAppId(@RequestBody String appId){
        List<Map> result = engineService.listEnginesByAppId(appId);
        return WebUtils.success(result);
    }

    @ApiOperation(value = "查询所有渲染定义")
    @PostMapping("listAllEngines")
    public WebResult listAllEngines(@RequestBody EngineQueryDTO engineQueryDTO){
        List<Map> result = engineService.listAllEngines(
                "%"+engineQueryDTO.getAppId()+"%",
                "%"+engineQueryDTO.getCode()+"%",
                "%"+engineQueryDTO.getName()+"%"
        );
        return WebUtils.success(result);
    }

}
