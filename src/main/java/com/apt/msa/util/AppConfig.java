package com.apt.msa.util;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
//@ComponentScan("com.apt.msa")
public class AppConfig extends WebMvcConfigurerAdapter {
 
	@Override
	public void addCorsMappings(CorsRegistry registry) {
 		 /*registry.addMapping("/apt/**")
	   	  .allowedOrigins("http://localhost:9000", "http://localhost:8080")
		 // .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
		//  .allowedHeaders("X-Auth-Token", "Content-Type")
		    .allowCredentials(false)
		  .maxAge(4800);*/
		super.addCorsMappings(registry);
       //LOGGER.info("WebMVC configuration : addCorsMappings");
        registry.addMapping("/apt/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
