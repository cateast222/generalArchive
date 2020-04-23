package com.ebs.platform.business.web;

import com.ebs.platform.business.domain.professionentity.ArchiveFile;
import com.ebs.platform.business.dto.archive.*;
import com.ebs.platform.business.mylog.MyLog;
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
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * 档案主表管理控制
 * @author liuji
 */
@Api(value = "档案信息", description = "档案信息")
@RestController
@RequestMapping("archives")
public class ArchivesController extends BaseController {

    @Autowired
    private ArchivesService archivesService;

    @ApiOperation(value = "查询新增/修改档案表单字段信息")
    @PostMapping("/queryArchivesForm")
    public WebResult queryArchivesAdd(@RequestBody ArchiveFormDTO archiveFormDTO) {
        Map maps = archivesService.queryArchiveFormColumn(archiveFormDTO);
        return WebUtils.success(maps);
    }

    @ApiOperation(value = "新增/修改档案信息")
    @PostMapping("/saveArchives")
    public WebResult saveArchives(@RequestBody ArchivesDTO archivesDTO) {
        Integer id = archivesService.saveArchives(archivesDTO);
        return WebUtils.success(id);
    }

    @ApiOperation(value = "查询列表信息")
    @PostMapping("/queryByFilter")
    @MetadataFunction
    public WebResult<QueryResponse<List<ArchivesDTO>>> queryByFilter(@RequestBody QueryRequest<ArchivesDTO> req) throws IOException, ClassNotFoundException {
        return WebUtils.success(archivesService.queryByFilter(req));
    }

    @ApiOperation(value = "查询树形列表信息")
    @PostMapping("/queryArchives")
    public WebResult<List<Map>> queryArchives(@RequestBody ArchiveFormDTO archiveFormDTO){
        return WebUtils.success(archivesService.queryArchives(archiveFormDTO));
    }

    @ApiOperation(value = "查询上下架档案信息")
    @PostMapping("/queryRoomArchives")
    public WebResult queryRoomArchives(@RequestBody ArchiveRoomParamDTO archiveRoomParamDTO){
        return WebUtils.success(archivesService.queryRoomArchives(archiveRoomParamDTO));
    }

    @ApiOperation(value = "查询某个档案门类的列表信息")
    @PostMapping("/queryByArchiveType")
    public WebResult<List<ArchivesShowDTO>> queryByArchiveType(@RequestBody Integer archiveType) {
        List<ArchivesShowDTO> list = archivesService.queryByArchiveType(archiveType);
        return WebUtils.success(list);
    }

    @MyLog("删除档案")
    @ApiOperation(value = "删除档案")
    @PostMapping("/deleteArchives")
    public WebResult deleteArchives(@RequestBody Integer id) {
        archivesService.deleteArchives(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "上传原文")
    @PostMapping("/archvieFileUpload")
    public WebResult archvieFileUpload(@RequestParam("files") MultipartFile[] files,Integer id) throws Exception {
        Map map = new HashMap();
        if(files==null || files.length<=0){
            return WebUtils.error("上传原文不能为空");
        }

        map.put("id",id);
        map.put("files",files);
        archivesService.archvieFileUpload(map);
        return WebUtils.success();
    }

    @ApiOperation(value = "删除原文")
    @PostMapping("/deleteArchiveFile")
    public WebResult deleteArchiveFile(@RequestBody Map map) throws Exception {
        archivesService.deleteArchiveFile(map);
        return WebUtils.success();
    }

    @ApiOperation(value = "查询ocr")
    @PostMapping("/queryOcrArchives")
    @MetadataFunction
    public WebResult<QueryResponse<List<OcrArchivesDTO>>> queryOcrArchives(@RequestBody QueryRequest<OcrArchivesDTO> req){
        QueryResponse<List<OcrArchivesDTO>> response = archivesService.queryOcrArchives(req);
        return WebUtils.success(response);
    }

    @ApiOperation(value = "查询档案鉴定数据")
    @PostMapping("/queryIdentifyArchives")
    @MetadataFunction
    public WebResult<QueryResponse<List<ArchivesPastDTO>>> queryIdentifyArchives(@RequestBody QueryRequest<ArchivesPastDTO> req){
        QueryResponse<List<OcrArchivesDTO>> response = archivesService.queryIdentifyArchives(req);
        return WebUtils.success();
    }

    /**
     * 获取列表字段和查询条件表单
     * @param archivesQueryParamDTO
     * @return
     */
    @ApiOperation("获取列表字段和查询条件表单，接口定义 : @RequestMapping,@GetMapping,@PostMapping,@MetadataFunction...")
    @RequestMapping(value = "/getTableAndSelectForm",method = {RequestMethod.POST})
    public WebResult<FunctionInfo> queryFunctions(@RequestBody ArchivesQueryParamDTO archivesQueryParamDTO) throws IOException, ClassNotFoundException {
        FunctionInfo functionInfo = null;
        List<ServiceInfo> serviceInfoList = PackageUtil.getControllers(null);
        System.out.println("packUtil的size是: "+serviceInfoList.size());
        for (ServiceInfo svc : serviceInfoList) {
            System.out.println("svc的Name: "+svc.name);
            if(svc.functions!=null && svc.functions.size()!=0){
                System.out.println("svc的functions 不为空的："+svc.functions.get(0).name);
                Optional<FunctionInfo> fun = svc.functions.stream().filter(n->n.getName().equals(archivesQueryParamDTO.getFunctionName())).findFirst();
                if(fun.isPresent()) {
                    functionInfo = fun.get();
                    //return WebUtils.success(functionInfo);
                }
            }
        }
        if(null == functionInfo){
            return WebUtils.success();
        }else {
            functionInfo = archivesService.queryChildField(functionInfo,archivesQueryParamDTO,ArchivesController.class);
            return WebUtils.success(functionInfo);
        }
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
