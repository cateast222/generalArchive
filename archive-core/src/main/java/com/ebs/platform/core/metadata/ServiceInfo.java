package com.ebs.platform.core.metadata;

import java.util.List;

/**
 * 用于描述 Controller 的数据结构
 */
public class ServiceInfo {

    /**
     * 指 Controller 的 class.FullName
     */
    public String name;

    /**
     * 指 @RequestMapping 的 name 值
     */
    public String requestMapping;

    /**
     * 指 Controller 的描述，取自 @Api 的 description
     */
    public String description;

    /**
     * 指 Controller 中每个指定了 @RequestMapping 的 Class.Method
     */
    public List<FunctionInfo> functions;

    public ServiceInfo(){}

    public ServiceInfo(String name,String requestMapping,String description,List<FunctionInfo> functions){
        this.name=name;
        this.requestMapping=requestMapping;
        this.functions=functions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestMapping() {
        return requestMapping;
    }

    public void setRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }

    public List<FunctionInfo> getFunctions() {
        return functions;
    }

    public void setFunctions(List<FunctionInfo> functions) {
        this.functions = functions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "name='" + name + '\'' +
                ", requestMapping='" + requestMapping + '\'' +
                ", description='" + description + '\'' +
                ", functions=" + functions +
                '}';
    }
}
