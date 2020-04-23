package com.ebs.platform.business.service;


import com.ebs.platform.core.old.UserContextDTO;

import java.util.List;
import java.util.Map;

/**
 * 基础服务
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 9:09
 */
public interface IBaseService {
    /**
     * 获取登陆用户上下文
     * @return
     */
    UserContextDTO getUserContext();

    /**
     * 原生Sql查询
     * @param sql
     * @return
     */
    List<Map> sqlQuery(String sql);

    /**
     * 原生Sql查询，带参数
     * @param sql
     * @param params
     * @return
     */
    List<Map> sqlQuery(String sql, Object[] params);
    /**
     * 原生Sql查询，带参数
     * @param sql
     * @param params
     * @param firstResult
     * @param lastResult
     * @return
     */
    List<Map> sqlQuery(String sql, Object[] params, int firstResult, int lastResult);
}
