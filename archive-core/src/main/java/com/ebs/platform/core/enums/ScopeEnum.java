package com.ebs.platform.core.enums;

import sun.awt.SunHints;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-21
 * @desc 资源范围
 */
public enum  ScopeEnum implements IEnum{

    Platform(0,"平台",false),
    Application(1,"应用",false),
    Tenant(2,"租户",false),
    User(3,"用户",false);

    private int value;
    private String label;
    private boolean jsonIgonre;

    ScopeEnum(int value,String label,boolean jsonIgonre){
        this.value=value;
        this.label=label;
        this.jsonIgonre=jsonIgonre;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
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

    public static ScopeEnum valueOf(Integer value){
        for(ScopeEnum e : ScopeEnum.values()){
            if(e.getValue()== value)
                return e;
        }
        return null;
    }

}
