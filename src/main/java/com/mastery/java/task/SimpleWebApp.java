package com.mastery.java.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =
        DataSourceAutoConfiguration.class)
public class SimpleWebApp {

    public static void main(final String[] args) {
        SpringApplication.run(SimpleWebApp.class, args);
    }
}
