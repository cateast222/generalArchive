package com.ebs.platform.core.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用于在 DictionaryKey 返回一个请求时的处理，由于无法在返回response前修改里面的值，因此该功能暂不启用
 * liubo 2018.12.12 17:55
 */
@Configuration
public class WebRequestConfigureAdapter implements WebMvcConfigurer {

    @Autowired
    private DictionaryInterceptor interceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/configuration/ui")
                .excludePathPatterns("/configuration/security")
                .excludePathPatterns("/v2/api-docs");
    }
}
