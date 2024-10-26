package com.moneygement.lyf.jarvis.route.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class GraphHopperConfig {

    @Value("${graph-hopper.api-key}")
    private String graphHopperApiKey;

    @Value("${url.graph-hopper}")
    private String graphHopperUrl;
}

