package com.ebs.platform.business.dto.engine;
/**
 * 引擎sql数据源
 * @author 496382223@qq.com 白の狐狸
 * @date 2019/1/03 9:53
 */
public class EngineDataDTO {
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "EngineDataDTO{" +
                "sql='" + sql + '\'' +
                '}';
    }
}
