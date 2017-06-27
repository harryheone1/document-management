package com.bethesda;


import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.bethesda.business.DocumentManagementWorkflow;
import com.bethesda.common.error.ErrorInfoEexception;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) throws BeansException, ErrorInfoEexception {
    	ConfigurableApplicationContext context = SpringApplication.run(BootApplication.class, args);
        context.getBean(DocumentManagementWorkflow.class).refreshDictionary(); // run during start

    }

}
