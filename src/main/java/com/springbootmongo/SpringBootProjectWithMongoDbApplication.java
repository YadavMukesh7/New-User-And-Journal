package com.springbootmongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@SpringBootApplication
public class SpringBootProjectWithMongoDbApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootProjectWithMongoDbApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        System.out.println("Active profile is-> " + environment.getActiveProfiles()[0]);
        log.info("Active profile is-> {} ", environment.getActiveProfiles()[0]);
    }
}
