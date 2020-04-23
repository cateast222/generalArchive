package com.ebs.platform.business;

import com.ebs.platform.core.conf.ExtJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author
 */

@ComponentScan(value = "com.ebs.platform.core")
@ComponentScan(value = "com.ebs.platform.business")
@EnableJpaAuditing
@EnableJpaRepositories(repositoryFactoryBeanClass = ExtJpaRepositoryFactoryBean.class)
@SpringBootApplication()
@EnableCaching
public class ArchiveSystemApplication  extends SpringBootServletInitializer{
	/**
	 * 提供跨域支持
	 * @return
	 */
	@Bean
	public FilterRegistrationBean corsFilter(){
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**",config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

//	public static void main(String[] args) {
//		SpringApplication.run(ArchiveSystemApplication.class, args);
//	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ArchiveSystemApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(ArchiveSystemApplication.class, args);
	}
}
