package com.moneygement.lyf.jarvis.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOriginPatterns("*", "http://localhost:8080", "http://localhost:5173", "/**",
				"https://localhost:5173", "http://127.0.0.1:5173")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.allowedHeaders("*")
			.allowCredentials(true);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/docs/**")
			.addResourceLocations("classpath:/static/docs/");
		registry.addResourceHandler("/**")
			.addResourceLocations("classpath:/templates/");
		registry.addResourceHandler("/favicon.ico")
			.addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/**")
			.addResourceLocations("classpath:/static/");
	}

}
