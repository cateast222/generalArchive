package com.ebs.platform.core.query;

import com.ebs.platform.core.enums.ResourceType;

import java.lang.annotation.*;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-10
 * @desc 描述一个字段成员的UI配置信息
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetadataField {

    /**
     * 显示名称
     * @return
     */
    String name() default "未命名";

    /**
     * 资源类型
     * @return
     */
    @Deprecated
    String resourceType() default ResourceType.Default;

    /**
     * 是否为数据主键
     * @return
     */
    boolean isKey() default false;

    /**
     * 是否显示,改用 isColumn
     * @return
     */
    @Deprecated
    boolean isShow() default true;

    /**
     * 是否作为表格列显示
     * @return
     */
    boolean isColumn() default true;

    /**
     * 是否作为查询条件
     * @return
     */
    boolean isFilter() default true;

    /**
     * 是否隐藏该查询条件
     * @return
     */
    boolean isFilterHidden() default false;

}
