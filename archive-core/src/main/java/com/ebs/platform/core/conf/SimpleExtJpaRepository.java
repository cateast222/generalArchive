package com.ebs.platform.core.conf;

import com.ebs.platform.core.query.DTOHelp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

import static com.ebs.platform.core.util.PackageUtil.getUserContext;

/**
 * Created by liujie on 2019/11/28.
 */
public class SimpleExtJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExtJpaRepository<T, ID> {
    private final EntityManager em;

    public SimpleExtJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.em = em;
    }

    public SimpleExtJpaRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    @Override
    @Transactional
    public T dynamicSave(ID id, T entity) {
        T managedEntity = null;
        if(id!=null){
                managedEntity = this.getOne(id);
        }
        T mergedEntity;
        if (managedEntity == null) {
            em.persist(entity);
            mergedEntity = entity;
        } else {
            BeanUtils.copyProperties(entity, managedEntity, new String[]{"createTime","operator"});
            em.merge(managedEntity);
            mergedEntity = managedEntity;
        }
        return mergedEntity;
    }
    /**
     * object T 类型对应的 DTO
     * 用DTO 保存
     * */
    @Transactional
    @Override
    public T dynamicSaveDTO(ID id, Object object) {
        Class<?> entityDTO = object.getClass();
        DTOHelp annotation1 = entityDTO.getAnnotation(DTOHelp.class);
        if(annotation1 == null) return null;
        Class<?> aClass =  annotation1.sourceEntity();
        T sourceEntity;
        Object o =null;
        o = getObject(aClass, o);
        if (o == null) return null;
        BeanWrapper sourceWrapper ;
        sourceEntity = (T) o;
        sourceWrapper = setOperator(id,sourceEntity);
        BeanWrapper wrapperDTO = PropertyAccessorFactory.forBeanPropertyAccess(object);
        Field[] declaredFields = entityDTO.getDeclaredFields();

        for(Field field: declaredFields){
            DTOHelp annotation = field.getAnnotation(DTOHelp.class);
            String propertyDTO ;
            if(annotation == null)propertyDTO = field.getName();
            else propertyDTO = annotation.target();
            Object propertyValue = wrapperDTO.getPropertyValue(field.getName());
            sourceWrapper.setPropertyValue(propertyDTO,propertyValue);
        }
        if(id ==null)em.persist(sourceEntity);
        else em.merge(sourceEntity);
        return sourceEntity;
    }
    /**
     * 用 map 保存
     * sourceEntity T 类型的 class
     *
     * */
    @Transactional
    @Override
    public T dynamicSaveMap(Class<T> sourceEntity,Map<String,Object> map) {
        T source = null;
        Object idObj = map.get("id");
        Object object = getObject(sourceEntity, source);
        if(object ==null) return null;
        source = (T)object;
        ID id =null;
        if(idObj !=null)id = (ID) idObj;
        BeanWrapper sourceWrapper = setOperator(id, source);
        sourceWrapper.setPropertyValues(map);
        if(id ==null)em.persist(sourceEntity);
        else em.merge(sourceEntity);
        return source;
    }


    public String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
    /**
     * 获取 T 的实例对象
     * */
    private Object getObject(Class<?> aClass, Object o) {
        try {
            o = aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(o==null) return null;
        return o;
    }
    /**
     * 设置创建时间，创建人，修改时间，修改人
     * */
    private BeanWrapper setOperator(ID id,T sourceEntity){
        Date date = new Date();
        String userId = getUserContext().getUserId();
        BeanWrapper sourceWrapper;
        if(id !=null){
            sourceEntity = this.getOne(id);
            sourceWrapper = PropertyAccessorFactory.forBeanPropertyAccess(sourceEntity);
            sourceWrapper.setPropertyValue("updateTime",date);
            sourceWrapper.setPropertyValue("updateMan",userId);
        }else{
            sourceWrapper = PropertyAccessorFactory.forBeanPropertyAccess(sourceEntity);
            sourceWrapper.setPropertyValue("createTime",date);
            sourceWrapper.setPropertyValue("operator",userId);
        }
        return sourceWrapper;
    }
}
