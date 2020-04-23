package com.ebs.platform.core.query;

import java.util.List;

/**
 * @author liubo   18855815@qq.com
 * @date 2018-09-10
 * @desc
 */
public class MetadataFunctionDTO {
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

    @Override
    public String toString() {
        return "MetadataDTO{" +
                "requestField=" + requestField +
                ", responseField=" + responseField +
                '}';
    }
}
