package com.app;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

// TODO: Practical bonus 2: 
// "com.app" scanning whole directory is wastage as it will scan all directories
// WebConfiguration.java should move to app.com.config package
// scanning component should change from "com.app" to ""com.app.config"

@SpringBootApplication(scanBasePackages = {  "com.app.controller","com.app.repository.covid",
		"com.app.entity","com.app.service","com.app"})
@Slf4j
public class StartCovidApplication {

	// start everything
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(StartCovidApplication.class, args);

		log.info("Let's inspect the beans provided by Bayi Bot Mining v 0.2");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {

			if (beanName.indexOf("customer") > -1) {
				log.info("configured service name:={}", beanName);
			}

		}

	}

}