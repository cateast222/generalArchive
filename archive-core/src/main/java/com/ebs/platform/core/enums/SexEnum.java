package com.ebs.platform.core.enums;

import com.ebs.platform.core.exception.BusinessException;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-21
 * @desc 性别
 */
public enum SexEnum implements IEnum {

    Unknown(0, "未知",false),Male(1, "男",false), Female(2, "女",false);

    private int value;
    private String label;
    private boolean jsonIgonre;

    SexEnum(int value, String label,boolean jsonIgonre) {
        this.value = value;
        this.label = label;
        this.jsonIgonre=jsonIgonre;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean isJsonIgonre() {
        return jsonIgonre;
    }

    public void setJsonIgonre(boolean jsonIgonre) {
        this.jsonIgonre = jsonIgonre;
    }

    public static SexEnum valueOf(Integer value){
        for(SexEnum e : SexEnum.values()){
            if(e.getValue()==value)
                return e;
        }
        throw new BusinessException("给定的枚举值不正确。");
    }
}
