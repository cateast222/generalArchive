package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.archive.ArchiveBorrowByFind;
import com.ebs.platform.business.dto.archive.BorrowApplyDto;
import com.ebs.platform.business.service.IBorrowApplyService;
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
 * @Author: LiuWY
 * @Date: 2019/11/29 11:11
 */
@Api(value = "",description = "")
@RestController
@RequestMapping("borrowApply")
public class BorrowApplyController {

    @Autowired
    private IBorrowApplyService iBorrowApplyService;


    @ApiOperation(value = "新增-借阅")
    @PostMapping("add")
    public WebResult add(@RequestBody BorrowApplyDto borrowApplyDto){
        iBorrowApplyService.add(borrowApplyDto);
        return WebUtils.success();
    }

    @ApiOperation(value = "删除-借阅")
    @PostMapping("remove")
    public WebResult remove(@RequestBody Integer id){
        iBorrowApplyService.remove(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "归还-借阅")
    @PostMapping("doReturn")
    public WebResult doReturn(@RequestBody Integer id){
        iBorrowApplyService.doReturn(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "催还-借阅")
    @PostMapping("reminderReturn")
    public WebResult reminderReturn(@RequestBody Integer id){
        iBorrowApplyService.reminderReturn(id);
        return WebUtils.success();
    }

    @ApiOperation(value = "查询-借阅主表单记录")
    @PostMapping("queryByFilter")
    public WebResult<QueryResponse<List<BorrowApplyDto>>> queryByFilter(@RequestBody QueryRequest<ArchiveBorrowByFind> req){

        return WebUtils.success(iBorrowApplyService.queryByFilter(req));
    }

    @ApiOperation(value = "查询-借阅详细记录")
    @PostMapping("queryById")
    public WebResult<Object> queryById(@RequestBody Integer id){

        return WebUtils.success(iBorrowApplyService.queryById(id));
    }


}
