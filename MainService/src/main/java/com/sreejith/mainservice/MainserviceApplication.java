package com.sreejith.mainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MainserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainserviceApplication.class, args);
	}


	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

}
