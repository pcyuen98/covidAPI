package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(scanBasePackages = { "com.app.controller", "com.app.repository.covid", "com.app.entity",
		"com.app.service", "com.app" })
@Slf4j
public class StartCovidApplication {

	// start everything
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(StartCovidApplication.class, args);

	}

}