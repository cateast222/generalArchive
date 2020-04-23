package com.ebs.platform.business.settings;

import com.ebs.platform.core.conf.BaseSettings;
import com.ebs.platform.core.conf.Settings;

//@Settings("string")
public class SystemSetting extends BaseSettings {
    private String Name;
    private String Logo;
    private String Desc;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }
}
