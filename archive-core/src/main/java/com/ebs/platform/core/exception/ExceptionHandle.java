package com.ebs.platform.core.exception;

import com.ebs.platform.core.util.WebResult;
import com.ebs.platform.core.util.WebUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * BusinessException  100 : 业务逻辑不符要求，而产生的异常
 * Exception         -100 : 系统错误而发生的异常，此类异常应避免
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 15:10
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public WebResult Handle(Exception e){
        if(e instanceof BusinessException){
            return WebUtils.error(100,e.getMessage());
        }else if(e instanceof DataIntegrityViolationException) {
            if(e.getCause() instanceof ConstraintViolationException){
                return WebUtils.error(-100,((ConstraintViolationException)e.getCause()).getSQLException().getMessage());
            }else {
                return WebUtils.error(-100, ((DataIntegrityViolationException) e).getMessage());
            }
        }else if(e.getCause() instanceof SQLGrammarException) {
            return WebUtils.error(-100, ((SQLGrammarException) e.getCause()).getSQLException().getMessage());
        }else{
            e.printStackTrace();
            return WebUtils.error(-200, e.getMessage());
        }
    }

}
