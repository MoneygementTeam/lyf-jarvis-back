package com.moneygement.lyf.jarvis.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class OpenWeatherConfig {
	@Value("${open-weather.api-key}")
	private String openWeatherApiKey;

	@Value("${url.open-weather}")
	private String openWeatherUrl;
}
