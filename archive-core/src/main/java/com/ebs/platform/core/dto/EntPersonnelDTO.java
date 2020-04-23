package com.ebs.platform.core.dto;

import java.util.Date;

/**
 * 企业人员
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:45
 */
public class EntPersonnelDTO {
    @Override
    public String toString() {
        return "EntPersonnelDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", entId='" + entId + '\'' +
                ", entDeptId='" + entDeptId + '\'' +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", sex=" + sex +
                ", remark='" + remark + '\'' +
                '}';
    }

    private String id;
    private String userId;
    private String entId;
    private String entDeptId;
    private String name;
    private Date birthDay;
    private String tel;
    private String mail;
    private String sex;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getEntDeptId() {
        return entDeptId;
    }

    public void setEntDeptId(String entDeptId) {
        this.entDeptId = entDeptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
