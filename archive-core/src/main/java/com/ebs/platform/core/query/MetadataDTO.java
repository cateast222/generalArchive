package com.ebs.platform.core.query;

import java.util.List;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-10
 * @desc
 */
public class MetadataDTO {

    private String name;

    private String description;

    /**
     * 请求参数集合
     */
    private List<MetadataFieldDTO> requestField;

    /**
     * 接口响应后的参数集合，用于描述返回数据集的数据结构
     */
    private List<MetadataFieldDTO> responseField;

    public List<MetadataFieldDTO> getRequestField() {
        return requestField;
    }

    public void setRequestField(List<MetadataFieldDTO> requestField) {
        this.requestField = requestField;
    }

    public List<MetadataFieldDTO> getResponseField() {
        return responseField;
    }

    public void setResponseField(List<MetadataFieldDTO> responseField) {
        this.responseField = responseField;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MetadataDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", requestField=" + requestField +
                ", responseField=" + responseField +
                '}';
    }
}
