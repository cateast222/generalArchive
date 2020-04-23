package com.ebs.platform.core.enums;


/**
 * 权限类别
 */
public enum PowerTypeEnum implements IEnum {
    Default(0,"分类项",false), Menu(10,"菜单组",false),MenuItem(11,"菜单项",false), Router(12,"路由",false),  Button(20,"按钮",false),Data(30,"数据",true),Org(40,"公司",true),OrgDept(45,"部门",true),Api(90,"接口",true);

    private int value;

    private String label;

    private boolean jsonIgonre;

    PowerTypeEnum(int value,String lable,boolean jsonIgonre){
        this.value=value;
        this.label=lable;
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
}
