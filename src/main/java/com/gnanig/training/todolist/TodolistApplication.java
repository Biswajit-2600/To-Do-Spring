package com.gnanig.training.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gnanig.training.todolist.controller",
        "com.gnanig.training.todolist.services", "com.gnanig.training.todolist.database"})
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

}
