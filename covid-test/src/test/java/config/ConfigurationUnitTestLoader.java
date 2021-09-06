package config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
@ActiveProfiles(profiles = "unitTest")
@SpringBootApplication(scanBasePackages = { "com" })
@Slf4j
public class ConfigurationUnitTestLoader {

	@EventListener
	public void handleContextRefreshed(ContextRefreshedEvent event) {
		printActiveProperties((ConfigurableEnvironment) event.getApplicationContext().getEnvironment());
	}

	private void printActiveProperties(ConfigurableEnvironment env) {

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

	@Autowired
	ApplicationContext context;

	@Test
	public void contextLoads() {

		String[] beanNames = context.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {

			if (beanName.indexOf("covid") > -1) {
				log.info("configured service name:={}", beanName);
			}
		}

		printActiveProperties((ConfigurableEnvironment) context.getEnvironment());
	}



}
