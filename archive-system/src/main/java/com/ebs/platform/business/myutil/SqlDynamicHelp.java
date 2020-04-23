package com.ebs.platform.business.myutil;

import com.ebs.platform.core.query.MetadataField;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.*;

/**
 * jap 原生sql 动态参数 自动封装 分页查询 帮助类
 * */
public class SqlDynamicHelp<T> {

    /**
     * 接收查询数据的实体类
     * */
    private Class<T> viewEntity ;

    public SqlDynamicHelp(Class<T> viewEntity){this.viewEntity = viewEntity;}
    /**
     * entityManager jpa 的实体类管理器
     * entity 参数实体类
     * pageble 分页
     * nativeDataSql 原生的 sql （“不带”查询条件）
     * nativeCountSql 原生查询数量的sql （“不带”查询条件）
     * whereMap 存储where条件和对应参数的集合 key:对应entity中的属性名称 value:sql 条件
     * 过滤 参数是实体类
     * */
    public Page<T> getQueryData(EntityManager entityManager,Object entity, Pageable pageble, String nativeDataSql, String nativeCountSql, Map<String,String> whereMap){
        Class<?> aClass ;
        Field[] declaredFields = null;
        BeanWrapper beanWrapper =null;
        Map<String,Object> parameterMap = new HashMap();
        StringBuffer sqlWhere = new StringBuffer();
        if(entity !=null){
            aClass = entity.getClass();
            declaredFields = aClass.getDeclaredFields();
            beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(entity);
            int where = nativeDataSql.toLowerCase().indexOf("where");
            if( where < 1)sqlWhere.append(" where 1 = 1 ");
            for(Field field : declaredFields){
                MetadataField annotation = field.getAnnotation(MetadataField.class);
                if(annotation == null) continue;
                String name = field.getName();
                Object propertyValue = beanWrapper.getPropertyValue(name);
                if(propertyValue ==null) continue;
                appendWhere(whereMap, sqlWhere, parameterMap, name, propertyValue);
            }
        }
        PageImpl<T> pageData = this.getPageData(entityManager, nativeDataSql, nativeCountSql, sqlWhere, parameterMap, pageble);
        return pageData;
    }
    /**
     * 重载 用Map<String,Object> 代替实体类
     * */
    public Page<T> getQueryData(EntityManager entityManager,Map<String,Object> entity, Pageable pageble, String nativeDataSql, String nativeCountSql, Map<String,String> whereMap){
        StringBuffer sqlWhere = new StringBuffer();
        Map<String,Object> parameterMap = new HashMap();
        int where = nativeDataSql.toLowerCase().indexOf("where");
        if( where < 1)sqlWhere.append(" where 1 = 1 ");
        Set<String> keySet= entity.keySet();
        for(String key:keySet){
            Object objectValue = entity.get(key);
            appendWhere(whereMap, sqlWhere, parameterMap, key, objectValue);
        }
        PageImpl<T> pageData = this.getPageData(entityManager, nativeDataSql, nativeCountSql, sqlWhere, parameterMap, pageble);
        return pageData;
    }
    /**
     * 拼接where条件 收集参数
     * */
    private void appendWhere(Map<String, String> whereMap, StringBuffer sqlWhere, Map<String, Object> parameterMap, String key, Object objectValue) {
        parameterMap.put(key,objectValue);
        String whereValue = whereMap.get(key);
        sqlWhere.append(" ");
        sqlWhere.append( whereValue );
        sqlWhere.append(" ");
    }
    /**
     * 设置分页 设置参数 查询出数据 将数据封装成实体类对象
     * */
    private PageImpl<T> getPageData(EntityManager entityManager,String nativeDataSql,String nativeCountSql,StringBuffer sqlWhere,Map<String,Object> parameterMap,Pageable pageble){
        Query dataQuery = entityManager.createNativeQuery(nativeDataSql + sqlWhere);
        Query countQuery = entityManager.createNativeQuery(nativeCountSql + sqlWhere);
        Set<String> strings = parameterMap.keySet();
        for (String key :strings){
            Object o = parameterMap.get(key);
            dataQuery.setParameter(key,o);
            countQuery.setParameter(key,o);
        }
        //设置分页
        Long offset = pageble.getOffset();
        int pageIndex = offset.intValue();
        int pageSize = pageble.getPageSize();
        dataQuery.setFirstResult(pageIndex);
        dataQuery.setMaxResults(pageSize);
        //返回数据
        BigInteger singleResult =(BigInteger) countQuery.getSingleResult();
        long total = singleResult.longValue();
        List<Map> resultList = total > offset ? dataQuery.getResultList(): Collections.emptyList();
        //转换成实体类
        MapToDTO<T> mapToDTO = new MapToDTO();
        //viewEntity.
        List<T> archiveAuditDTOS = mapToDTO.mapToDTO(this.viewEntity,resultList);
        PageImpl<T> pageArchiveAuditDTOS = new PageImpl<>(archiveAuditDTOS, pageble, total);
        return pageArchiveAuditDTOS;
    }
}
