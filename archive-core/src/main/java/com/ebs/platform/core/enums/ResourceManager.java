package com.ebs.platform.core.enums;

import com.ebs.platform.core.query.IResourceQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-11
 * @desc 管理所有IResourceQuery实例
 */
public class ResourceManager {

    private static ResourceManager instance;

    private final Map<String,Class<IResourceQuery>> resourcesType;
    //private final Map<String,String> resourcesType;

    private ResourceManager() {
        resourcesType=new HashMap<String,Class<IResourceQuery>>();
        //resourcesType=new HashMap<String,String>();
    }

    public static ResourceManager getInstance(){

        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    /**
     * 注册IResourceQuery 对象
     * @param key 来自于ResourceType中的定义
     * @param value
     */
    public void register(String key,Class value){
        if(resourcesType.containsKey(key))
            return;
        resourcesType.put(key,value);
    }

    /**
     * 执行 IresourceQuery对象的query方法。
     * @param key 来自于ResourceType的定义
     * @return
     */
    public List<ResourceData> queryResource(String key){
        List<ResourceData> ret = new ArrayList<ResourceData>();
        Class<IResourceQuery>  cls = resourcesType.get(key);
        if(cls==null)
            return ret;

        try {
            return cls.newInstance().query();
        }catch(Exception ex){}

        return ret;

    }

}

