package com.rakib.api_geteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

    public static void main(String[] args) throws URISyntaxException {
        SpringApplication.run(ApiGatewayApplication.class, args);

        System.out.println("API CALL URL: " + new URI("http://localhost:9090/service-name/api-path"));
        System.out.println("Actuator Health Check URL: " + new URI("http://localhost:9090/service-name/actuator/health"));
    }

}
