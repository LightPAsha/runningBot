package com.runningclub.runningbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RunningBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningBotApplication.class, args);
    }

}
