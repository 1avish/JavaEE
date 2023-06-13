package com.example.eurekathree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaThreeApplication.class, args);
    }

}
