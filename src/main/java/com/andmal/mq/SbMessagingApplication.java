package com.andmal.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SbMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbMessagingApplication.class, args);
    }

}
