package com.hongda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class IEProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(IEProjectApplication.class, args);
    }
}
