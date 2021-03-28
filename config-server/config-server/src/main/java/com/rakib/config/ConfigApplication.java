package com.rakib.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigApplication {
	private final static Logger LOGGER = LoggerFactory.getLogger(ConfigApplication.class);
	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(ConfigApplication.class, args);
		LOGGER.info("Main Env Profile: "+ new URI("http://localhost:8888/service-name/default"));
		LOGGER.info("Dev Env Profile: "+ new URI("http://localhost:8888/service-name/dev"));
		LOGGER.info("Test Env Profile: "+ new URI("http://localhost:8888/service-name/test"));
	}

}
