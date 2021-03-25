package com.hb.udemy.microservice.currencyconversionservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.hb.udemy.microservice.currencyconversionservice")
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication {
	private static final Logger LOGGER = LogManager.getLogger(CurrencyConversionServiceApplication.class);
	
	public static void main(String[] args) {
		LOGGER.info("Info level log message");
        LOGGER.debug("Debug level log message");
        LOGGER.error("Error level log message");
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

}
