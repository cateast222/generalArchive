package com.ebs.platform.core.enums;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-11
 * @desc 数据资源的定义，每个SpringBoot应继承此类，继续扩展
 */
public class ResourceType {
    /**
     * 数据来源于系统的数据字典
     */
    public static final String Dictionary="Resource_Dictionary";

    /**
     * 数据来源于一个枚举类型的定义
     */
    public static final String Enum="Resource_Enum";

    /**
     * 当前企业的部门数据
     */
    public static final String Dept="Resource_Dept";

    /**
     * 当前企业的人员数据
     */
    public static final String Personnel="Resource_Personnel";

    /**
     * 当前租户的角色数据
     */
    public static final String Role="Resource_Role";

    /**
     * 未定义
     */
    public static final String Default="Not_Define";
}
