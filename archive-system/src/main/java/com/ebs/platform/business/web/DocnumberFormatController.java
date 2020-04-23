package com.ebs.platform.business.web;


import com.ebs.platform.business.domain.professionentity.DocnumberFormat;
import com.ebs.platform.business.dto.archive.TableConfigParamDTO;
import com.ebs.platform.business.service.DocnumberFormatService;
import com.ebs.platform.business.service.TableConfigService;
import com.ebs.platform.core.query.MetadataField;
import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(value = "档案格式",description = "档案格式")
@RestController
@RequestMapping("docnumberFormat")
public class DocnumberFormatController {
    @Autowired
    private DocnumberFormatService formatService;
    @Autowired
    private TableConfigService configService;
    @ApiOperation(value = "根据档案类型查询 档案格式生成规则")
    @PostMapping("/findByArchiveTypeId")
    public WebResult findByArchiveTypeId(@RequestBody Map map){
        List<DocnumberFormat> byArchiveId = formatService.findByArchiveId(map);
        return WebUtils.success(byArchiveId);
    }
    @ApiOperation(value="保存档案格式")
    @RequestMapping("/save")
    public WebResult save(@RequestBody DocnumberFormat docnumberFormat){
        formatService.saveDocnumberFormat(docnumberFormat);
        return WebUtils.success();
    }

    @ApiOperation(value="获取数据项")
    @RequestMapping("/getDataItem")
    public WebResult getDataItem(@RequestBody TableConfigParamDTO dto) throws ClassNotFoundException {
        String packName = "com.ebs.platform.business.domain.professionentity.Archives";
        List<Map> maps = new ArrayList<>();
        Class<?> cls = Class.forName(packName);
        Field[] fields = cls.getDeclaredFields();
        for(Field field: fields){
            Map<String,String> map = new HashMap();
            MetadataField annotation = field.getAnnotation(MetadataField.class);
            if( annotation == null) continue;
            map.put("id",field.getName());
            map.put("name",annotation.name());
            maps.add(map);
        }
        List<Map> maps1 = configService.queryDocNumberData(dto);
        maps.addAll(maps1);
        return WebUtils.success(maps);
    }

    @ApiOperation(value = "根据编号删除档号格式")
    @PostMapping("/deleteById")
    public WebResult deleteById(@RequestBody Integer id){
        formatService.deleteDocnumberFormat(id);
        return WebUtils.success();
    }
    @ApiOperation(value = "根据id查询档号格式")
    @PostMapping("/findById")
    public WebResult findById(@RequestBody Integer id){
        DocnumberFormat docnumberFormat = formatService.findbyId(id);
        return WebUtils.success(docnumberFormat);
    }

}
