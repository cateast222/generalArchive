package com.ebs.platform.core.query;

import com.ebs.platform.core.enums.ResourceData;

import java.util.List;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-11
 * @desc 资源查询接口
 */
public interface IResourceQuery {

    /**
     * 执行对资源的查询，返回资源数据
     * @return
     */
    List<ResourceData> query();
}
