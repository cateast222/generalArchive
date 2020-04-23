package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.archive.ArchiveTypeDTO;
import com.ebs.platform.business.dto.archive.EntityTypeDTO;
import com.ebs.platform.business.mylog.MyLog;
import com.ebs.platform.business.service.EntityTypeService;
import com.ebs.platform.core.BaseController;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 实体分类管理控制
 * @author liuji
 */
@Api(value = "实体分类", description = "实体分类")
@RestController
@RequestMapping("entityType")
public class EntityTypeController extends BaseController {

    @Autowired
    private EntityTypeService entityTypeService;

    @ApiOperation(value = "查询所有实体分类")
    @PostMapping("/queryEntityType")
    public WebResult queryEntityType(@RequestBody EntityTypeDTO entityTypeDTO) {
        List<Map> entityTypeDTOS = entityTypeService.queryAll(Integer.parseInt(entityTypeDTO.getEntityFonds()));
        return WebUtils.success(entityTypeDTOS);
    }

    @ApiOperation(value = "保存和修改实体分类")
    @PostMapping("/saveEntityType")
    public WebResult saveEntityType(@RequestBody EntityTypeDTO entityTypeDTO) {
        return WebUtils.success(entityTypeService.saveEntityType(entityTypeDTO));
    }

    @MyLog("删除实体分类")
    @ApiOperation(value = "删除实体分类")
    @PostMapping("/deleteEntityType")
    public WebResult deleteEntityType(@RequestBody EntityTypeDTO entityTypeDTO) {
        entityTypeService.deleteEntityType(entityTypeDTO.getId());
        return WebUtils.success();
    }

    @ApiOperation(value = "查询实体分类显示下拉框")
    @PostMapping("/queryEntityTypeSelectData")
    public WebResult queryEntityTypeSelectData(@RequestBody EntityTypeDTO entityTypeDTO){
        return WebUtils.success(entityTypeService.queryEntityTypeSelectData(entityTypeDTO));
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
