package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.archive.ArchiveTypeDTO;
import com.ebs.platform.business.dto.archive.ArchiveTypeRequestParamDTO;
import com.ebs.platform.business.mylog.MyLog;
import com.ebs.platform.business.service.ArchiveTypeService;
import com.ebs.platform.core.BaseController;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * 档案门类管理控制
 * @author liuji
 */
@Api(value = "档案门类", description = "所有档案的类型")
@RestController
@RequestMapping
public class ArchiveTypeController extends BaseController {

    @Autowired
    private ArchiveTypeService archiveTypeService;

    @MyLog("查询所有门类树形结构")
    @ApiOperation(value = "查询所有门类树形结构")
    @PostMapping("archiveType/queryAllType")
    public WebResult queryAllType(@RequestBody(required = false) ArchiveTypeRequestParamDTO archiveTypeRequestParamDTO) throws IOException,ClassNotFoundException {
        List<ArchiveTypeDTO> archiveTypeDTOS = archiveTypeService.queryAll(archiveTypeRequestParamDTO);
        return WebUtils.success(archiveTypeDTOS);
    }

    @ApiOperation(value = "保存和修改档案门类")
    @PostMapping("archiveType/saveArchiveType")
    public WebResult saveArchiveType(@RequestBody ArchiveTypeDTO archiveTypeDTO) {
        return WebUtils.success(archiveTypeService.saveArchiveType(archiveTypeDTO));
    }

    @MyLog("删除档案门类")
    @ApiOperation(value = "删除档案门类")
    @PostMapping("archiveType/deleteArchiveType")
    public WebResult deleteArchiveType(@RequestBody ArchiveTypeDTO archiveTypeDTO) {
        archiveTypeService.deleteArchiveType(archiveTypeDTO.getId());
        return WebUtils.success();
    }

    @ApiOperation(value = "查询档案父类显示下拉框")
    @PostMapping("archiveType/queryParentType")
    public WebResult queryParentType(){
        return WebUtils.success(archiveTypeService.queryAllParent());
    }

    @ApiOperation(value = "查询具体档案类别树")
    @PostMapping("archiveType/queryAllChildType")
    public WebResult queryAllChildType(){
        return WebUtils.success(archiveTypeService.queryAllChildType());
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
