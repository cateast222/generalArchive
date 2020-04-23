package com.ebs.platform.business.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 简单日志
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 15:57
 */
@Entity(name = "tb_app_log")
@Data
public class AppLogDO {

    @Id
    private String id;

    private String username; //用户名

    private String operation; //操作

    private String method; //方法名

    private String params; //参数

    private String ip; //ip地址

    private Date createDate; //操作时间
    //创建getter和setter方法






















//    @Override
//    public String toString() {
//        return "AppLogDO{" +
//                "id='" + id + '\'' +
//                ", appName='" + appName + '\'' +
//                ", entName='" + entName + '\'' +
//                ", personnelName='" + personnelName + '\'' +
//                ", Message='" + Message + '\'' +
//                ", logDate=" + logDate +
//                '}';
//    }
//
//    @Id
//    @Column(nullable = false, length = 40)
//    private String id;
//
//    /**
//     * 应用名称
//     */
//    @Column(name = "app_name",length = 40)
//    private String appName;
//
//    /**
//     * 企业名称
//     */
//    @Column(name = "ent_name",length = 40)
//    private String entName;
//
//    /**
//     * 人员名称
//     */
//    @Column(name = "personnel_name",length = 40)
//    private String personnelName;
//
//    /**
//     * 日志消息
//     */
//    @Column(name = "message",length = 255)
//    private String Message;
//
//    /**
//     * 产生时间
//     */
//    @Column(name = "start_date", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date logDate;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getAppName() {
//        return appName;
//    }
//
//    public void setAppName(String appName) {
//        this.appName = appName;
//    }
//
//    public String getEntName() {
//        return entName;
//    }
//
//    public void setEntName(String entName) {
//        this.entName = entName;
//    }
//
//    public String getPersonnelName() {
//        return personnelName;
//    }
//
//    public void setPersonnelName(String personnelName) {
//        this.personnelName = personnelName;
//    }
//
//    public String getMessage() {
//        return Message;
//    }
//
//    public void setMessage(String message) {
//        Message = message;
//    }
//
//    public Date getLogDate() {
//        return logDate;
//    }
//
//    public void setLogDate(Date logDate) {
//        this.logDate = logDate;
//    }
}
