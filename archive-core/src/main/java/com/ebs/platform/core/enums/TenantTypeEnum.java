package com.ebs.platform.core.enums;

import com.ebs.platform.core.exception.BusinessException;

/**
 * 租户类型
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/8/31 9:44
 */
public enum TenantTypeEnum implements IEnum {
    /**
     * 企业租户
     */
    Enterprise(1,"企业租户",false),
    /**
     * 个人租户
     */
    Personal(2,"个人租户",false);

    private int value;
    private String label;
    private boolean jsonIgonre;
    TenantTypeEnum(int value,String label,boolean jsonIgonre){
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

    @Override
    public boolean isJsonIgonre() {
        return jsonIgonre;
    }

    public void setJsonIgonre(boolean jsonIgonre) {
        this.jsonIgonre = jsonIgonre;
    }

    public static TenantTypeEnum valueOf(Integer value){
        for(TenantTypeEnum e : TenantTypeEnum.values()){
            if(e.getValue()==value)
                return e;
        }
        throw new BusinessException("无效的枚举值。");
    }
}
