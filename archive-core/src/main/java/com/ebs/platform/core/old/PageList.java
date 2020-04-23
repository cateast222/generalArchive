package com.ebs.platform.core.old;

/**
 * @author lwy
 */
public class PageList {
    private Integer page;//第几页，数据库是从第0页开始,但是数据坐标是从一开始的
    private Integer size;//每页多少条数据
    private Long totalElements;//总共多少条数据

    @Override
    public String toString() {
        return "PageList{" +
                "page=" + page +
                ", size=" + size +
                ", totalElements=" + totalElements +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
