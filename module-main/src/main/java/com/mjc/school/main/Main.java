package com.mjc.school.main;

import com.mjc.school.controller.WebConfig;
import com.mjc.school.controller.implementation.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        try {
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebConfig.class);
            Application application = applicationContext.getBean("application", Application.class);
            application.runLoop();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}