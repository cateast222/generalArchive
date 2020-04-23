package com.ebs.platform.core.enums;

import com.ebs.platform.core.dto.AppDictValueDTO;
import com.ebs.platform.core.metadata.EnumInfo;
import com.ebs.platform.core.util.PackageUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-11
 * @desc 用于描述一个数据资源
 */
@Data
public class ResourceData {

    private String key;

    private String value;

    private String label;

    private String parentKey;

    private List<ResourceData> child;

    public ResourceData(String key, String value, String label) {
        this.key = key;
        this.value = value;
        this.label = label;
    }

    public ResourceData(String key, String value, String label, String parentKey) {
        this.key = key;
        this.value = value;
        this.label = label;
        this.parentKey = parentKey;
    }

    public ResourceData(String key, String value, String label, String parentKey, List<ResourceData> child) {
        this.key = key;
        this.value = value;
        this.label = label;
        this.parentKey = parentKey;
        this.child = child;
    }

    /**
     * 将枚举转为资源列表
     * @param key 枚举类型名或所在对象的成员名称
     * @param valueType 枚举类型
     * @return 资源
     */
    public static List<ResourceData> fromEnum(String key, Class valueType) {
        List<ResourceData> result = new ArrayList();
        if(!valueType.isEnum()) return result;

        //for (EnumInfo e : PackageUtil.getEnums(valueType.getClass())) {
        for (EnumInfo e : PackageUtil.getEnums(valueType)) {
            result.add(new ResourceData(key, e.getName(), e.getLabel()));
        }
        return result;
    }

    /**
     * 将字典值列表，转换为资源列表
     * @param key 字典代码
     * @param values 字典值列表
     * @return 资源
     */
    public static List<ResourceData> fromDictionary(String key, List<AppDictValueDTO> values) {
        List<ResourceData> result = new ArrayList();
        for (AppDictValueDTO value : values) {
            result.add(new ResourceData(key, value.getValue(), value.getName()));
        }
        return result;
    }
}
