package com.ebs.platform.business.web;

import com.ebs.platform.business.domain.professionentity.Fonds;
import com.ebs.platform.business.domain.professionentity.Watermark;
import com.ebs.platform.business.service.IWatermarkService;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

/**
 * 水印设置 lwy
 */

@Api(value = "水印设置", description = "水印设置")
@RestController
@RequestMapping("watermark")
public class WatermarkController {

    @Autowired
    private IWatermarkService iWatermarkService;

    @ApiOperation(value = "保存-水印")
    @PostMapping("save")
    public WebResult save(@RequestBody Watermark watermark){
        iWatermarkService.save(watermark);
        return WebUtils.success();
    }

    @ApiOperation(value = "根据ID查询-水印")
    @PostMapping("findById")
    public WebResult findById(@RequestBody String id){

        return WebUtils.success(iWatermarkService.findById(id));
    }

    @ApiOperation(value = "条件查询-水印")
    @GetMapping("queryByFilter")
    public WebResult queryByFilter(@RequestParam(required = false)  String param){

        return WebUtils.success(iWatermarkService.queryByFilter(param));
    }

    @ApiOperation(value = "下拉列表-查询-水印")
    @PostMapping("findBySelect")
    public WebResult findBySelect(){

        return WebUtils.success(iWatermarkService.findBySelect());
    }
}
