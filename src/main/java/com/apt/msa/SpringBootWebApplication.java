package com.apt.msa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootWebApplication.class);
	
	@Autowired
	private DataSource datasource;
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }

    public static void main(String[] args) throws Exception {
    	logger.debug("Apt Application Started");
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		
		Class clazz = SpringBootWebApplication.class;
		
		ScriptRunner scriptrunner = new ScriptRunner(datasource.getConnection());
		
		scriptrunner.runScript(new BufferedReader(new FileReader(clazz.getResource("/plan_details.sql").getFile())));
	}

}