package com.ebs.platform.business.service;

import com.ebs.platform.business.dto.archivestatistics.ArchiveStatisticsRequestDTO;
import com.ebs.platform.business.dto.archivestatistics.StatisticsBorrowerRequestDTO;

import java.util.List;
import java.util.Map;

public interface IArchiveStatisticsService {

    Map<String, List<Object>> statisticsArchiveByFilter(ArchiveStatisticsRequestDTO requestDTO );

    Map<String ,List<Object>> statisticsBorrower(StatisticsBorrowerRequestDTO requestDTO);
}
