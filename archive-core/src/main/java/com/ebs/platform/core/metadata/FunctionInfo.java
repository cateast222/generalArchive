package com.ebs.platform.core.metadata;

import com.ebs.platform.core.query.MetadataFieldDTO;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controller 中所有 @RequestMapping 的 method 数据结构
 */
public class FunctionInfo {

    /**
     * 指 method.name
     */
    public String name;

    /**
     * 指 @ApiOperation 中的描述
     */
    public String description;

    /**
     * 指 @RequestMapping 中的请求映射
     */
    private String requestMapping;

    /**
     * 指 @RequestMapping 的 method 类型，如 get，post 等
     */
    public RequestMethod[] method;

    /**
     * 指 method 中的输入参数集合
     */
    private List<MetadataFieldDTO> request;

    /**
     * 指 mthod 中的输出参数集合（对于复杂类型，如果是简单类型，则 List 中只有一个值）
     */
    private List<MetadataFieldDTO> response;

    public FunctionInfo(){}

    public FunctionInfo(String name,String description,String requestMapping,RequestMethod[] method,List<MetadataFieldDTO> request,
                        List<MetadataFieldDTO> response) {
        this.name = name;
        this.description = description;
        this.requestMapping = requestMapping;
        this.method = method;
        this.request = request;
        this.response = response;
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

    public String getRequestMapping() {
        return requestMapping;
    }

    public void setRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }

    public RequestMethod[] getMethod() {
        return method;
    }

    public void setMethod(RequestMethod[] method) {
        this.method = method;
    }

    public List<MetadataFieldDTO> getRequest() {
        return request;
    }

    public void setRequest(List<MetadataFieldDTO> request) {
        this.request = request;
    }

    public List<MetadataFieldDTO> getResponse() {
        return response;
    }

    public void setResponse(List<MetadataFieldDTO> response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "FunctionInfo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", requestMapping='" + requestMapping + '\'' +
                ", method=" + method +
                ", request=" + request +
                ", response=" + response +
                '}';
    }
}
