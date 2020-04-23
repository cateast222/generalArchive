package com.ebs.platform.business.myutil;

import com.ebs.platform.core.BaseAutoIncrementEntity;

import java.util.Date;

import static com.ebs.platform.core.util.PackageUtil.getUserContext;

public class MyUtil {
    public void save(BaseAutoIncrementEntity baseEntity){
        Integer id = baseEntity.getId();
        Date date = new Date();
        String userId = getUserContext().getUserId();
        if(id == null){
            baseEntity.setCreateTime(date);
            baseEntity.setOperator(userId);
        }else{
            baseEntity.setUpdateTime(date);
            baseEntity.setUpdateMan(userId);
        }
    }

}
