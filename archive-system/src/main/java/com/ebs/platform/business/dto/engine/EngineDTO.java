package com.ebs.platform.business.dto.engine;

import java.util.Date;
/**
 * 引擎传输对象
 * @author 496382223@qq.com 白の狐狸
 * @date 2019/1/03 9:53
 */
public class EngineDTO {

    /**
     * Id
     */
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 应用中的唯一编码(同应用中不允许重复)
     */
    private String code;

    /**
     * 所属应用ID
     */
    private String appId;

    /**
     * 模型内容(前台DES加密后的内容)
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 创建账号ID
     */
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        return "EngineDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", appId='" + appId + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                ", userId='" + userId + '\'' +
                '}';
    }
}
