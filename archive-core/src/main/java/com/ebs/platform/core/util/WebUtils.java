package com.ebs.platform.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 控制器返回值帮助类
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/6/4 10:23
 */
@SuppressWarnings("unchecked")
public class WebUtils {
    private static final String DEFAULT_SUCCESS_MESSAGE = "Success";

    public static WebResult success(){
        return new WebResult();
    }
    /**
     * 当正确时返回的值，数据与信息
     * @param code
     * @param data
     * @param message
     * @return
     */
    public static WebResult success(int code, Object data,String message){
        WebResult result = new WebResult();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);

        return result;
    }


    /**
     * 当正确时返回的数据
     * @param data
     * @return
     */
    public static WebResult success(Object data){
        return success(0,data,DEFAULT_SUCCESS_MESSAGE);
    }
    /**
     * 当错误时返回的值与信息
     * @param code
     * @param msg
     * @return
     */
    public static WebResult error(int code, String msg){
        WebResult result = new WebResult();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    /**
     * 错误时的返回信息
     * @param msg
     * @return
     */
    public static WebResult error(String msg){
        WebResult result = new WebResult();
        result.setCode(-1);
        result.setMessage(msg);
        return result;
    }

    /**
     * 错误时返回的异常信息
     * @param ex
     * @return
     */
    public static WebResult error(Exception ex){
        WebResult result = new WebResult();
        result.setCode(ex.hashCode());
        result.setMessage(ex.getMessage());
        return result;
    }

    /**
     * 序列化成JsonString
     * @param webResult
     * @return
     * @throws JsonProcessingException
     */
    public static String getString(WebResult webResult) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(webResult);
    }

    public static void sendMessage(HttpServletResponse response, String message) throws IOException {
        sendMessage(response,-1,message);
    }
    public static void sendMessage(HttpServletResponse response,int code, String message) throws IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setDateHeader("Expires", 0);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        WebResult result = WebUtils.error(code,message);
        response.getWriter().write(WebUtils.getString(result));
    }
}


