package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.archive.ArchiveAuditDTO;
import com.ebs.platform.business.mylog.MyLog;
import com.ebs.platform.business.service.ArchiveIdentifyService;
import com.ebs.platform.core.BaseController;
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
 * 档案鉴定管理控制
 * @author liuji
 */
@Api(value = "档案鉴定", description = "档案鉴定")
@RestController
@RequestMapping("archiveIdentify")
public class ArchiveIdentifyController extends BaseController {

    @Autowired
    private ArchiveIdentifyService archiveIdentifyService;

    @MetadataFunction
    @ApiOperation(value = "查询所有档案鉴定信息")
    @PostMapping("/queryArchiveIdentify")
    public WebResult<QueryResponse<List<ArchiveAuditDTO>>> queryArchiveIdentify(@RequestBody QueryRequest<ArchiveAuditDTO> request) {
        QueryResponse<List<ArchiveAuditDTO>> listQueryResponse = archiveIdentifyService.queryAllByDeleted(request);
        return WebUtils.success(listQueryResponse);
    }

    @ApiOperation(value = "保存和修改档案鉴定信息")
    @PostMapping("/saveArchiveIdentify")
    public WebResult saveArchiveIdentify(@RequestBody ArchiveAuditDTO dto) {
        return WebUtils.success(archiveIdentifyService.saveArchiveIdentify(dto));
    }

    @MyLog("删除档案鉴定信息")
    @ApiOperation(value = "删除档案鉴定信息")
    @PostMapping("/deleteArchiveIdentify")
    public WebResult deleteEntityType(@RequestBody Integer id) {
        archiveIdentifyService.deleteArchiveIdentify(id);
        return WebUtils.success();
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
