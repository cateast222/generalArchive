package com.ebs.platform.business.myutil;

import com.ebs.platform.core.query.DTOHelp;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapToDTO<T> {

    public List<T> mapToDTO(Class<T> tClass,List<Map> maps){
        List<T> tList = new ArrayList<>();
        Object o = null;
        try {
            o= tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(o==null) return null;
        T entity = (T)o;
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(entity);
        for(Map map:maps){
            Field[] declaredFields = tClass.getDeclaredFields();
            for(Field field:declaredFields){
                DTOHelp annotation = field.getAnnotation(DTOHelp.class);
                String key ;
                if(annotation == null) key = field.getName();
                else key = annotation.target();
                beanWrapper.setPropertyValue(key,map.get(key));
            }
            tList.add(entity);
        }
        return tList;
    }
}
