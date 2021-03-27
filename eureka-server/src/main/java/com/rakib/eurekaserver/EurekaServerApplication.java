package com.rakib.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) throws URISyntaxException {
        SpringApplication.run(EurekaServerApplication.class, args);
        System.out.println("Url: "+ new URI("http://localhost:8761/"));
    }

}
