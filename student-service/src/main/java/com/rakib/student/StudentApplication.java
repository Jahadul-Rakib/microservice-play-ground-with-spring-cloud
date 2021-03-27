package com.rakib.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.net.URI;

@Slf4j
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class StudentApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Swagger URL: " + new URI("http://localhost:8081/swagger-ui/"));
    }
}
