package com.springsecurity.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//@ComponentScan("com.springsecurity.*")
@ComponentScans({
		@ComponentScan("com.springsecurity.config"),
		@ComponentScan("com.springsecurity.controller"),
		@ComponentScan("com.springsecurity.entity"),
		@ComponentScan("com.springsecurity.repository"),
		@ComponentScan("com.springsecurity.security")
})
@SpringBootApplication
@EnableWebMvc
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
