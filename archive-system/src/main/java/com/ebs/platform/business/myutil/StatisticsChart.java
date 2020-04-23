package com.ebs.platform.business.myutil;

import java.util.*;

public class StatisticsChart {
    /**
     * table echart 图形数据转换方法
     * */
    public static Map<String, List<Object>> getData(List<Map> maps,StatisticsMapUtil mapUtil){
        Map<String,List<Object>> viewData = new HashMap<>();
        List<Object> tabData = new ArrayList<>();
        List<Object> typeDatas = new ArrayList<>();
        List<Object> typeNames = new ArrayList<>();
        for (Map map:maps){
            Map mp = new HashMap();
            mp.putAll(map);
            Object typeName =mapUtil.updateMap(mp);
            typeNames.add(typeName);
            tabData.add(mp);
        }
        mapUtil.setTypeData(typeDatas);
        viewData.put("tabData",tabData);
        viewData.put("typeData",typeDatas);
        viewData.put("typeName",typeNames);
        return viewData;
    }

}
