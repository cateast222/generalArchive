package com.ebs.platform.business.web;

import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.ArchiveRoomByAdd;
import com.ebs.platform.business.mylog.MyLog;
import com.ebs.platform.business.service.IArchiveRoomService;
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
 * @author lwy
 * @Date 2019-06-06 11:34
 */
@Api(value = "档案室管理",description = "档案室的管理")
@RestController
@RequestMapping("archiveroom")
public class ArchiveRoomController {

    @Autowired
    private IArchiveRoomService iArchiveRoomService;

    @MyLog("档案室新增")
    @ApiOperation(value = "新增")
    @PostMapping("add")
    public WebResult add(@RequestBody ArchiveRoomByAdd roomDTO){
        Integer id = iArchiveRoomService.add(roomDTO);
        return WebUtils.success(id);
    }

    @MyLog("档案室修改")
    @ApiOperation(value = "修改")
    @PostMapping("update")
    public WebResult update(@RequestBody ArchiveRoomByAdd roomDTO){
        iArchiveRoomService.update(roomDTO);
        return WebUtils.success();
    }

    @MyLog("档案室删除")
    @ApiOperation(value = "删除")
    @PostMapping("remove")
    public WebResult remove(@RequestBody Integer id){
        iArchiveRoomService.remove(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "查询")
    @PostMapping("findAll")
    public WebResult findAll(){

        return WebUtils.success(iArchiveRoomService.findAll());
    }

    @ApiOperation(value = "查询-到虚拟的格子级别")
    @PostMapping("findAllBySelect")
    public WebResult findAllBySelect(){

        return WebUtils.success(iArchiveRoomService.findAllBySelect());
    }

    @ApiOperation(value = "根据柜子id查询使用详情")
    @PostMapping("findUseById")
    public WebResult findUseById(@RequestBody Integer id){

        return WebUtils.success(iArchiveRoomService.findUseById(id));
    }

    @ApiOperation(value = "档案上架，下架，转移,可批量")
    @PostMapping("archiveMove")
    public WebResult archiveMove(@RequestBody List<Archives> archives){
        iArchiveRoomService.archiveMove(archives);
        return WebUtils.success();
    }

}
