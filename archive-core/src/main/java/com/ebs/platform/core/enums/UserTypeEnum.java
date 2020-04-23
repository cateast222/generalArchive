package com.ebs.platform.core.enums;

public enum  UserTypeEnum implements  IEnum{

    PlatformAdmin(1, "平台管理员",true),ApplicationAdmin(2,"应用管理员",false),TenantAdmin(3,"租户管理员",false),User(9,"一般用户",false);

    private int value;
    private String label;
    private boolean jsonIgonre;

    UserTypeEnum(int value, String label,boolean jsonIgonre) {
        this.value = value;
        this.label = label;
        this.jsonIgonre=jsonIgonre;
    }

    public static UserTypeEnum valueOf(Integer value){
        switch (value){
            case 1:
                return UserTypeEnum.PlatformAdmin;
            case 2:
                return UserTypeEnum.ApplicationAdmin;
            case 3:
                return UserTypeEnum.TenantAdmin;
            default:
                return UserTypeEnum.User;
        }
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
}
