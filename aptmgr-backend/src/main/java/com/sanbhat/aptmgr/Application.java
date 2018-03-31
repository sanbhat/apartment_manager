package com.sanbhat.aptmgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class Application extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AptMgrConfig.class);
    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(AptMgrConfig.class, args);
	}


}
