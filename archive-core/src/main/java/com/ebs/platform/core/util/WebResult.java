package com.ebs.platform.core.util;



/**
 * 控制器返回值
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:23
 */
public class WebResult<T>  {
    /**
     * 编码，0代表正常执行并返回，非0代表不正常（开发人员可以自行约定）
     */
    private int code = 0;

    /**
     * 返回的消息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
