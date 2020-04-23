package com.ebs.platform.core.conf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by liujie on 2019/11/28.
 */
@NoRepositoryBean
public interface ExtJpaRepository<T, ID extends Serializable> extends JpaRepository<T,ID> {

    /**
     * insert or dynamic update entity (will findOne first)
     * @param id entity id
     * @param entity entity
     * @return entity
     */
    T dynamicSave(ID id, T entity);
    /**
     * id 主键
     * entityDTO T实体类 对应的DTO
     * */
    T dynamicSaveDTO(ID id,Object entityDTO);

    T dynamicSaveMap(Class<T> sourceEntity,Map<String,Object> map);
}
