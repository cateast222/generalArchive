package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.archive.ArchiveTypeDTO;
import com.ebs.platform.business.dto.archive.TableConfigDTO;
import com.ebs.platform.business.dto.archive.TableConfigParamDTO;
import com.ebs.platform.business.mylog.MyLog;
import com.ebs.platform.business.service.TableConfigService;
import com.ebs.platform.core.BaseController;
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


/**
 * 档案分类元数据控制
 * @author liuji
 */
@Api(value = "档案分类元数据", description = "档案分类元数据")
@RestController
@RequestMapping
public class TableConfigController extends BaseController {

    @Autowired
    private TableConfigService tableConfigService;

    @MyLog("查询所有档案分类元数据信息")
    @ApiOperation(value = "查询所有档案分类元数据信息")
    @PostMapping("tableConfig/queryAll")
    public WebResult queryAllType(@RequestBody TableConfigDTO tableConifgDTO)  {
        List<TableConfigDTO> tableConifgDTOS = tableConfigService.queryAllByFilter(tableConifgDTO);
        return WebUtils.success(tableConifgDTOS);
    }

    @ApiOperation(value = "保存和修改档案分类元数据")
    @PostMapping("tableConfig/saveTableConfig")
    public WebResult saveTableConfig(@RequestBody TableConfigDTO tableConifgDTO) {
        return WebUtils.success(tableConfigService.saveTableConfig(tableConifgDTO));
    }

    @MyLog("删除档案分类元数据")
    @ApiOperation(value = "删除档案分类元数据")
    @PostMapping("tableConfig/deleteTableConfig")
    public WebResult deleteArchiveType(@RequestBody TableConfigDTO tableConfigDTO) {
        tableConfigService.deleteTableConfig(tableConfigDTO.getId());
        return WebUtils.success();
    }

    @ApiOperation(value = "根据id查询档案分类元数据")
    @PostMapping("tableConfig/queryTableConfigById")
    public WebResult queryTableConfigById(@RequestBody TableConfigDTO tableConfigDTO) {
        TableConfigDTO dto = tableConfigService.queryTableConfigById(tableConfigDTO.getId());
        return WebUtils.success(dto);
    }

    @ApiOperation(value = "查询档号格式数据项")
    @PostMapping("tableConfig/queryDocNumberData")
    public WebResult queryDocNumberData(@RequestBody TableConfigParamDTO tableConfigParamDTO) {
        List<Map> dto = tableConfigService.queryDocNumberData(tableConfigParamDTO);
        return WebUtils.success(dto);
    }

    @Override
    protected WebResult queryMetadata() {
        return null;
    }

    @Override
    protected WebResult queryMetadataByName(String name) {
        return null;
    }
}
