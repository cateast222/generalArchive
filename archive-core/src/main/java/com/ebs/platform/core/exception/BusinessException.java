package com.ebs.platform.core.exception;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/3 9:38
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message){
        super(message);
    }
}
