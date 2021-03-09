package com.app;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.app.repository.DefaultMasterBeanData;

import lombok.extern.slf4j.Slf4j;

@Import({ DefaultMasterBeanData.class })
@SpringBootApplication(scanBasePackages = {  "com.app.controller","com.app.repository.covid",
		"com.app.entity","com.app.service"})
@Slf4j
public class StartCovidApplication {

	// start everything
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(StartCovidApplication.class, args);

		log.info("Let's inspect the beans provided by Bayi Bot Mining v0.2:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {

			if (beanName.indexOf("customer") > -1) {
				log.info("configured service name:={}", beanName);
			}

		}

	}

}