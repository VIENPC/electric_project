package com.nhutin.electric_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nhutin.electric_project.GlobalInterceptor;


@Configuration
public class Config implements WebMvcConfigurer{
	@Autowired
    private GlobalInterceptor myInterceptor;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**", "/templates/**")
                .addResourceLocations("classpath:/static/", "classpath:/templates/")
                ;
    }
}
