package com.rakib.address;

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
public class AddressApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AddressApplication.class, args);
        System.out.println("From Address Service");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Swagger URL: "+ new URI("http://localhost:8082/swagger-ui/"));
    }
}
