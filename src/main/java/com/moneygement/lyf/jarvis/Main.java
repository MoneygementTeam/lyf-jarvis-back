package com.moneygement.lyf.jarvis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
@ConfigurationPropertiesScan
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        log.info("lyf-jarvis-back server is running successfully!");
    }
}
