package com.ebs.platform.business.web;

import com.ebs.platform.business.dto.archivestatistics.ArchiveStatisticsRequestDTO;
import com.ebs.platform.business.dto.archivestatistics.StatisticsBorrowerRequestDTO;
import com.ebs.platform.business.service.IArchiveStatisticsService;
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


@Api(value = "档案统计",description = "档案统计数据")
@RequestMapping("archiveStatistics")
@RestController()
public class ArchiveStatisticsController {

    @Autowired
    private IArchiveStatisticsService service;

    @ApiOperation(value ="用过滤条件查询 归档统计信息")
    @PostMapping("/queryByFilter")
    public WebResult statisticsArchiveByFilter(@RequestBody ArchiveStatisticsRequestDTO requestDTO){
        Map<String, List<Object>> stringListMap = service.statisticsArchiveByFilter(requestDTO);
        return WebUtils.success(stringListMap);
    }
    @PostMapping("/borrowerStatistics")
    @ApiOperation(value = "借阅统计")
    public WebResult statisticsBorrower(@RequestBody StatisticsBorrowerRequestDTO requestDTO){
        Map<String, List<Object>> stringListMap = service.statisticsBorrower(requestDTO);
        return WebUtils.success(stringListMap);
    }
}
