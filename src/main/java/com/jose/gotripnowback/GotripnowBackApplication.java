package com.jose.gotripnowback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class GotripnowBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(GotripnowBackApplication.class, args);
	}

}
