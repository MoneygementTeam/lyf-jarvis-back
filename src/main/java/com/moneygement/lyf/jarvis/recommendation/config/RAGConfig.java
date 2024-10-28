package com.moneygement.lyf.jarvis.recommendation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class RAGConfig {
	@Value("${url.rag}")
	private String ragUrl;
}
