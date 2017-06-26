package com.bethesda;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.bethesda.business.impl.DocumentManagementService;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
    	ConfigurableApplicationContext context = SpringApplication.run(BootApplication.class, args);
        context.getBean(DocumentManagementService.class).refreshDictionary(); // run during start

    }

}
