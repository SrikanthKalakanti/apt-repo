package com.apt.msa.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
 
	@Override
	public void addCorsMappings(CorsRegistry registry) {
 		super.addCorsMappings(registry);
        registry.addMapping("/apt/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
