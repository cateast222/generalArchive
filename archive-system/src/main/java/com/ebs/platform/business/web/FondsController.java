package com.ebs.platform.business.web;

import com.ebs.platform.business.domain.professionentity.Fonds;
import com.ebs.platform.business.dto.fonds.FondsDTO;
import com.ebs.platform.business.dto.fonds.FondsDTOByPage;
import com.ebs.platform.business.service.IFondsService;
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

/**
 * 全宗管理 lwy
 */
@Api(value = "全宗管理", description = "全宗管理")
@RestController
@RequestMapping("fonds")
public class FondsController {

    @Autowired
    private IFondsService iFondService;

    @ApiOperation(value = "保存-全宗(新增无ID，修改传ID)")
    @PostMapping("save")
    public WebResult save(@RequestBody Fonds fonds){
        iFondService.save(fonds);
        return WebUtils.success();
    }

    @ApiOperation(value = "根据ID查询-全宗")
    @PostMapping("findById")
    public WebResult findById(@RequestBody Integer id){
        return WebUtils.success(iFondService.findById(id));
    }

    @ApiOperation(value = "根据code查询-全宗")
    @PostMapping("findByCode")
    public WebResult findByCode(@RequestBody Fonds fonds){
        WebResult success = WebUtils.success(iFondService.findByCode(fonds));
        return success;
    }

    @ApiOperation(value = "根据过滤条件---查询全宗信息")
    @PostMapping("queryByFilter")
    @MetadataFunction
    public WebResult<QueryResponse<List<Fonds>>> queryByFilter(@RequestBody QueryRequest<FondsDTO> req){
        QueryResponse<List<Fonds>> listQueryResponse = iFondService.queryByFilter(req);
        WebResult success= WebUtils.success(listQueryResponse);
        return success;
    }
    @ApiOperation(value = "查询未删除的全宗信息")
    @PostMapping("/queryByDelete")
    public WebResult<List<FondsDTOByPage>> queryAll(){
        List<FondsDTOByPage> byDeleted = iFondService.findByDeleted(false);
        WebResult success= WebUtils.success(byDeleted);
        return success ;
    }

    @ApiOperation(value = "查询所有全宗号的编号和名称")
    @PostMapping("/getIdAndName")
    public WebResult getIdAndName(){
        List<Map> idAndName = iFondService.getIdAndName();
        WebResult success= WebUtils.success(idAndName);
        return success ;
    }
}
