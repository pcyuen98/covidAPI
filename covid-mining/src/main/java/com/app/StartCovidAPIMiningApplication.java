package com.app;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.app.mining.repository.DefaultMasterBeanData;

import lombok.extern.slf4j.Slf4j;

@Import({ DefaultMasterBeanData.class })
@SpringBootApplication(scanBasePackages = { "com.app.mining.repository", "com.app.mining.controller",
		"com.app.entity","com.app.mining.service"})
@Slf4j
public class StartCovidAPIMiningApplication {

	// start everything
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(StartCovidAPIMiningApplication.class, args);

		log.info("Let's inspect the beans provided by Spring Boot Data Mining v0.1:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {

			if (beanName.indexOf("bayi") > -1) {
				log.info("configured service name:={}", beanName);
			}
			//System.out.println(new Country().getCountryName()); // Remark by Rahim 8
		}


	}

}