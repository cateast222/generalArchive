package com.ebs.platform.core.enums;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-18
 * @desc
 */
public interface IEnum {

    /**
     * 枚举的整型值
     * @return
     */
    int getValue();

    /**
     * 枚举的中文描述
     * @return
     */
    String getLabel();

    /**
     * 在序列化给前端时，是否加呼略。
     * @return
     */
    boolean isJsonIgonre();

}
