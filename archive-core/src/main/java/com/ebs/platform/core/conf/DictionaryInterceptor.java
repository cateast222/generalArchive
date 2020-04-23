package com.ebs.platform.core.conf;

import com.ebs.platform.core.query.DictionaryKey;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 字典的验证与值处理
 */
@Deprecated
@Component
public class DictionaryInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//        if(handler instanceof HandlerMethod){
//            System.out.println();
//            System.out.println("Args :");
//
//            System.out.println(response);

//            for(Parameter obj : ((HandlerMethod)handler).getMethod().getParameters()){
//                System.out.println(obj.getName() + " : " + obj.getType());

//                for(Field field : obj.getType().getDeclaredFields()){
//                    DictionaryKey  annotation =  field.getAnnotation(DictionaryKey.class);
//                    if(annotation==null) continue;
//
//                    switch (annotation.valueStyle()){
//                        case value:
//                            field.set(handler,"value");
//                            break;
//                        case name:
//                            field.set(handler,"name");
//                            break;
//                        case valueAndName:
//                            field.set(handler,"value-name");
//                            break;
//                    }
//                    System.out.println("    " + field.getName() + " : " + field.getType() + "  =  " + field.get(handler));
//                }
//            }
//            System.out.println();
//        }
    }



}
