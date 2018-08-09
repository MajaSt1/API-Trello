package com.crud.tasks.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication /* extends SpringBootServletInitializer */ {

    public static void main(String[] args) { SpringApplication.run(TasksApplication.class, args); }

 /*  @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
        return application.sources(com.crud.tasks.app.TasksApplication.class);
    } */
}
