package com.ebs.platform.business.web;

import com.ebs.platform.business.domain.professionentity.ArchiveFile;
import com.ebs.platform.business.dto.archive.*;
import com.ebs.platform.business.mylog.MyLog;
import com.ebs.platform.business.service.ArchiveFileService;
import com.ebs.platform.business.service.ArchivesService;
import com.ebs.platform.core.BaseController;
import com.ebs.platform.core.metadata.FunctionInfo;
import com.ebs.platform.core.metadata.ServiceInfo;
import com.ebs.platform.core.query.MetadataFunction;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import com.ebs.platform.core.util.PackageUtil;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * 档案原文管理控制
 * @author liuji
 */
@Api(value = "原文管理", description = "原文管理")
@RestController
@RequestMapping("archiveFile")
public class ArchiveFileController extends BaseController {

    @Autowired
    private ArchiveFileService archiveFileService;

    @ApiOperation(value = "根据档案id查询文件信息")
    @PostMapping("/queryByArchiveId")
    public WebResult queryByArchiveId(@RequestBody Integer archiveId) {
        List<ArchiveFile> list = archiveFileService.queryByArchiveId(archiveId);
        return WebUtils.success(list);
    }

    @ApiOperation(value = "ocr文字识别")
    @PostMapping("/executeOcr")
    @ApiImplicitParam(name="dtos", value="文件实体类集合", required=true,allowMultiple=true, dataType = "{}")
    public WebResult executeOcr(@RequestBody ArchiveFileDTO[] dtos) throws TesseractException {
       archiveFileService.executeOcr(dtos);
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
