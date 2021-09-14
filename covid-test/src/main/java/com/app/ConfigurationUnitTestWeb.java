package com.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(scanBasePackages = { "com.app.controller", "com.app.repository.covid", "com.app.entity",
		"com.app.service", "com.app" })
public class ConfigurationUnitTestWeb {
	
	@Autowired
	ApplicationContext context;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(StartCovidTest.class, args);
		printActiveProperties((ConfigurableEnvironment) ctx.getEnvironment());
	}

	// TODO: advance, move this to util
	public static void printActiveProperties(ConfigurableEnvironment env) {

		System.out.println("************************* ACTIVE APP PROPERTIES ******************************");

		List<MapPropertySource> propertySources = new ArrayList<>();

		env.getPropertySources().forEach(it -> {
			if (it instanceof MapPropertySource && it.getName().contains("applicationConfig")) {
				propertySources.add((MapPropertySource) it);
			}
		});

		propertySources.stream().map(propertySource -> propertySource.getSource().keySet()).flatMap(Collection::stream)
				.distinct().sorted().forEach(key -> {
					try {
						System.out.println(key + "=" + env.getProperty(key));
					} catch (Exception e) {
						log.warn("{} -> {}", key, e.getMessage());
					}
				});
		System.out.println("******************************************************************************");
	}

}
