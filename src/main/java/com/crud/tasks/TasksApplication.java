package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages ={"com.crud.tasks.controller"})
public class TasksApplication /* extends SpringBootServletInitializer */ {

    public static void main(String[] args) { SpringApplication.run(TasksApplication.class, args); }

 /*  @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
        return application.sources(TasksApplication.class);
    } */
}
