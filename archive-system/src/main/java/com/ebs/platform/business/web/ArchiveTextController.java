package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.archivetext.ArchiveSearcherTextDTO;
import com.ebs.platform.business.dto.archivetext.ArchiveTextDTO;
import com.ebs.platform.business.dto.archivetext.ArchiveTextSaveDTO;
import com.ebs.platform.business.service.IArchiveTextService;
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
import java.util.Map;


@Api(value = "档案检索")
@RestController
@RequestMapping("archiveText")
public class ArchiveTextController {

    @Autowired
    private IArchiveTextService archiveTextService;

    @MetadataFunction
    @ApiOperation(value = "模糊匹配文档内容")
    @PostMapping("/queryArchiveText")
    public WebResult<QueryResponse<List<ArchiveTextDTO>>> queryArchiveText(@RequestBody QueryRequest<ArchiveSearcherTextDTO> text){
        QueryResponse<List<ArchiveTextDTO>> listQueryResponse = archiveTextService.queryArchiveText(text);
        return WebUtils.success(listQueryResponse);
    }

    @ApiOperation(value = "根据文件id 查找原文文本")
    @PostMapping("/queryByFileId")
    public WebResult queryByFileId(@RequestBody Integer fileId){
        Map<String, Object> stringObjectMap = archiveTextService.queryByFileId(fileId);
        return WebUtils.success(stringObjectMap);
    }

    @ApiOperation(value = "保存或修改原文内容")
    @PostMapping("/save")
    public WebResult save(@RequestBody ArchiveTextSaveDTO saveDTO){
        archiveTextService.save(saveDTO);
        return WebUtils.success();
    }

}
