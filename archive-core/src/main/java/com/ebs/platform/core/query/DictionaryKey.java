package com.ebs.platform.core.query;

import com.ebs.platform.core.enums.DictionaryValueStyleEnum;
import com.ebs.platform.core.enums.ScopeEnum;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DictionaryKey {

    /**
     * 字典的Code
     * @return
     */
    String key();

    /**
     * 字典的Name
     * @return
     */
    String name() default "";

    /**
     * 字典值的应用范围
     * @return
     */
    ScopeEnum scope() default ScopeEnum.Tenant;

//    /**
//     * 值的样式
//     * @return
//     */
//    DictionaryValueStyleEnum valueStyle() default DictionaryValueStyleEnum.value;
}
