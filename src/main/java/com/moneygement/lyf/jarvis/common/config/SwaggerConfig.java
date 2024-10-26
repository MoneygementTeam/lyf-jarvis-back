package com.moneygement.lyf.jarvis.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@EnableWebMvc
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Value("${server.protocol}")
    private String protocol;
    @Value("${server.host}")
    private String host;
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .addServersItem(new Server().url(protocol + "://" + host));
    }

}
