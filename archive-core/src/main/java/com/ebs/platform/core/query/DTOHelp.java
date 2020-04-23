package com.ebs.platform.core.query;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DTOHelp {
    /**
     * T 类型实体类中的属性名称
     * */
    String target() default "";

    Class<?> sourceEntity() default Object.class;

}
