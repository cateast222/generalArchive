package com.ebs.platform.business.domain;

import com.ebs.platform.core.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 引擎存储实体
 * content字段存储Base64编码的内容
 * @author 496382223@qq.com 白の狐狸
 * @date 2019/1/03 9:53
 */
@Entity(name = "tb_app_engine")
public class EngineDO extends BaseEntity {

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 应用中的唯一编码(同应用中不允许重复)
     */
    @Column(name = "code", nullable = false, length = 20)
    private String code;

    /**
     * 所属应用ID
     */
    @Column(name = "app_id",nullable = false)
    private String appId;

    /**
     * 模型内容(前台DES加密后的内容)
     */
    @Column(name = "content", nullable = false, columnDefinition  = "text")
    private String content;

    /**
     * 备注
     */
    @Column(name = "remark", length = 100)
    private String remark;

    /**
     * 创建日期
     */
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    /**
     * 创建账号ID
     */
    @Column(name = "user_id",nullable = false)
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "EngineDO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", appId='" + appId + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                ", userId='" + userId + '\'' +
                '}';
    }
}
