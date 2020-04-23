package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.ArchiveStatisticsDao;
import com.ebs.platform.business.dto.archivestatistics.ArchiveStatisticsRequestDTO;
import com.ebs.platform.business.dto.archivestatistics.StatisticsBorrowerRequestDTO;
import com.ebs.platform.business.myutil.StatisticsChart;
import com.ebs.platform.business.myutil.StatisticsMapUtil;
import com.ebs.platform.business.service.IArchiveStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ArchiveStatisticsServiceImpl implements IArchiveStatisticsService {

    @Autowired
    private ArchiveStatisticsDao statisticsDao;

    @Override
    public Map<String, List<Object>> statisticsArchiveByFilter(ArchiveStatisticsRequestDTO requestDTO) {
        String code = requestDTO.getCode();
        String year = requestDTO.getYear();
        Date start = requestDTO.getArchiveStart();
        Date end = requestDTO.getArchiveEnd();
        List<Map> maps = statisticsDao.statisticsArchiveByFilter(code,year,start,end);

        List<Object> archivesCounts = new ArrayList<>();
        List<Object> filesCounts = new ArrayList<>();
        List<Object> mountCounts = new ArrayList<>();
        List<Object> archivesSizes = new ArrayList<>();
        List<Object> filesSizes = new ArrayList<>();

        return StatisticsChart.getData(maps,new StatisticsMapUtil() {
            @Override
            public Object updateMap(Map map) {
                //由于sql查出来的 文件页数是重复添加的 所以要除以filesCount
                Object typeName = map.get("typeName");
                Object archivesCountObj = map.get("archivesCount");
                Object filesCountObj = map.get("filesCount");
                Object mountCountObj = map.get("mountCount");
                Object archivesSizeObj = map.get("archivesSize");
                Object filesSizeObj = map.get("filesSize");

                archivesCounts.add(archivesCountObj);
                filesCounts.add(filesCountObj);
                mountCounts.add(mountCountObj);
                archivesSizes.add(archivesSizeObj);

                Integer filesSize ;
                Integer filesCount = 0;

                if(filesSizeObj != null) filesSize = ((BigDecimal) filesSizeObj ).intValue();
                else {filesSizes.add(null);return typeName;}
                if(filesCountObj !=null)filesCount = ((BigDecimal) filesCountObj ).intValue();
                if(filesCount >0) filesSize = filesSize /filesCount;
                filesSizes.add(filesSize);
                return typeName ;
            }

            @Override
            public void setTypeData(List<Object> typeData) {
                typeData.add(archivesCounts);
                typeData.add(filesCounts);
                typeData.add(mountCounts);
                typeData.add(archivesSizes);
                typeData.add(filesSizes);
            }
        });
    }
    @Override
    public Map<String, List<Object>> statisticsBorrower(StatisticsBorrowerRequestDTO requestDTO) {
        List<Map> maps = statisticsDao.statisticsBorrower(requestDTO.getBorrowerStart(),requestDTO.getBorrowerEnd(),requestDTO.getUseParttern(),requestDTO.getBorrowPurpose(),requestDTO.getBorrowerType());
        List<Object> manTimes = new ArrayList<>();
        List<Object> tapes = new ArrayList<>();
        return StatisticsChart.getData(maps, new StatisticsMapUtil() {
                @Override
                public Object updateMap(Map map) {
                    Object typeName =map.get("typeName");
                    Object manTime = map.get("manTime");
                    Object tape = map.get("tape");
                    manTimes.add(manTime);
                    tapes.add(tape);
                    return typeName;
                }
            @Override
            public void setTypeData(List<Object> typeData) {
                typeData.add(manTimes);
                typeData.add(tapes);
            }
        });
    }
}
