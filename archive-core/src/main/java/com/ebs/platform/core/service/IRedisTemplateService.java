package com.ebs.platform.core.service;

public interface IRedisTemplateService {

     <T> boolean set(String key ,T value);

     <T> T get(String key,Class<T> clazz);

     <T> T stringToBean(String value, Class<T> clazz);

     <T> String beanToString(T value);
}
