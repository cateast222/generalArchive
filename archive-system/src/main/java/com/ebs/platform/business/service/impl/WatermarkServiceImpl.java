package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.IWatermarkDao;
import com.ebs.platform.business.domain.professionentity.Watermark;
import com.ebs.platform.business.service.IWatermarkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WatermarkServiceImpl extends BaseServiceImpl implements IWatermarkService {

    @Autowired
    private IWatermarkDao iWatermarkDao;

    @Override
    public void save(Watermark watermark) {

        if(watermark.getId() == null){
            watermark.setCreateTime(new Date());
            watermark.setOperator(getUserContext().getUserId());
        }else {
            watermark.setUpdateTime(new Date());
            watermark.setUpdateMan(getUserContext().getUserId());
        }
        iWatermarkDao.save(watermark);
    }

    @Override
    public Watermark findById(String id) {

        Watermark watermark = iWatermarkDao.queryByDeletedAndId(false,id);
        return watermark;
    }

    @Override
    public List<Watermark> queryByFilter(String param) {
        List<Watermark> watermarks;
        if(StringUtils.isBlank(param)){
            watermarks = iWatermarkDao.queryByDeleted(false);
        }else {
            watermarks = iWatermarkDao.queryByDeletedAndWatermarkCodeContainingOrWatermarkNameContaining(false,param,param);
        }
        return watermarks;
    }

    @Override
    public List<Map> findBySelect(){

        String sql = "select id as value , watermark_name as label from watermark where is_deleted=0 ";
        List<Map> maps = sqlQuery(sql);

        return maps;
    }


}
