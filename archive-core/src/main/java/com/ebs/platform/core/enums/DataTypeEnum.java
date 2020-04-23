package com.ebs.platform.core.enums;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-10
 * @desc 数据类型定义
 */
public enum DataTypeEnum implements IEnum {

    NONE(-1,"未知",false), OBJECT(0,"对象",false),STRING(1,"字符",false),NUMBER(2,"带小数的数值",false),INT(3,"整数值",false),DATE(4,"日期",false),DATETIME(5,"日期时间",false),MONEY(6,"金额",false);

    private int value;

    private String label;

    private boolean jsonIgonre;

    DataTypeEnum(int value,String lable,boolean jsonIgonre){
        this.value=value;
        this.label=lable;
        this.jsonIgonre=jsonIgonre;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isJsonIgonre() {
        return jsonIgonre;
    }

    public void setJsonIgonre(boolean jsonIgonre) {
        this.jsonIgonre = jsonIgonre;
    }
}
