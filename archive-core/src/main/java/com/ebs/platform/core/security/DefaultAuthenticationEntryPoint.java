package com.ebs.platform.core.security;

import com.ebs.platform.core.util.WebUtils;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 496382223@qq.com 白の狐狸
 * @date 2018/9/5 16:07
 */
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        if(e instanceof InsufficientAuthenticationException){
            WebUtils.sendMessage(response,"请求失败,未提供登陆凭证.");
        }else{
            WebUtils.sendMessage(response,e.getMessage());
        }
    }

}
