package com.ebs.platform.core.query;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-12
 * @desc 封装带分页的查询结果
 */
public class QueryResponse<T>  implements IMetadataObject {

    public QueryResponse(){

    }
    public QueryResponse(Page<T> obj,T data) {
        this.pageIndex = obj.getNumber();
        this.pageSize = obj.getSize();
        this.resultCount = obj.getTotalElements();
        this.respnoseData = data;
    }

    @MetadataField(name = "页大小")
    private long pageSize;

    @MetadataField(name = "当前而")
    private long pageIndex;

    @MetadataField(name = "记录数")
    private long resultCount;

    @Deprecated
    @MetadataField(name = "数据集")
    private T respnoseData;

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public T getRespnoseData() {
        return respnoseData;
    }

    public void setRespnoseData(T respnoseData) {
        this.respnoseData = respnoseData;
    }

    @Override
    public String toString() {
        return "QueryResponse{" +
                "pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", resultCount=" + resultCount +
                ", respnoseData=" + respnoseData +
                '}';
    }

}
