package com.ebs.platform.core.conf;

import java.util.TimeZone;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component
public class TimeZoneConfig {

	 @Bean
	 public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
	        return jacksonObjectMapperBuilder ->
	                jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone("GMT+8"));
	    }
}
