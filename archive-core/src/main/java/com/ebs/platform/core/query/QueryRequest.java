package com.ebs.platform.core.query;

import com.ebs.platform.core.BaseAuditingEntity;
import com.ebs.platform.core.BaseEntity;
import javassist.runtime.Desc;
import lombok.Data;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-11
 * @desc 查询请求的定义
 */
@Data
public class QueryRequest<T> implements IQueryRequest {

    @MetadataField(name = "分页大小")
    private Integer pageSize = 10;

    @MetadataField(name = "当前页")
    private Integer pageIndex = 0;

    @MetadataField(name = "全文搜索")
    private String searchText = null;

    @MetadataField(name = "查询条件")
    private T condition;

    private Integer archiveType;

    private Integer archiveLevel;

    private Integer archiveStatus;

    public Pageable toPageable() {

        if(this.getPageSize()<=0){
            this.setPageSize(10);
        }

        if(this.getPageIndex()>0) {
            this.pageIndex = this.pageIndex - 1;
        }

        if (this.condition instanceof BaseAuditingEntity) {
            return PageRequest.of(this.getPageIndex(), this.pageSize, Sort.Direction.DESC, "createdDate");
        } else if (this.condition instanceof BaseEntity) {
            return PageRequest.of(this.getPageIndex(), this.pageSize, Sort.DEFAULT_DIRECTION.DESC, "id");
        } else {
            try{
                if (this.condition.getClass().getDeclaredField("createdDate")!=null){
                    return PageRequest.of(this.getPageIndex(), this.pageSize, Sort.Direction.DESC, "createdDate");
                }else if(this.condition.getClass().getDeclaredField("id")!=null){
                    return PageRequest.of(this.getPageIndex()  , this.pageSize, Sort.DEFAULT_DIRECTION.DESC, "id");
                }else{
                    return PageRequest.of(this.getPageIndex()  , this.pageSize);
                }
            }catch (Exception e){ }

            return PageRequest.of(this.getPageIndex() , this.pageSize);
        }
    }

    public Pageable toPageable(Sort.Direction direction, String... properties) {

        if(this.getPageIndex()>0) {
            this.pageIndex = this.pageIndex - 1;
        }
        return PageRequest.of(this.getPageIndex() , this.pageSize, direction, properties);
    }
}
