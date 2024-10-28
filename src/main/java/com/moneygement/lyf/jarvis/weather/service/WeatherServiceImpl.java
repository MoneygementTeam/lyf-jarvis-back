package com.moneygement.lyf.jarvis.weather.service;

import static com.moneygement.lyf.jarvis.common.util.JsonUtil.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moneygement.lyf.jarvis.common.error.ExternalApiFailedException;
import com.moneygement.lyf.jarvis.common.util.HttpClientUtil;
import com.moneygement.lyf.jarvis.weather.config.OpenWeatherConfig;
import com.moneygement.lyf.jarvis.weather.domain.WeatherInformationResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
	private final OpenWeatherConfig openWeatherConfig;

	public WeatherInformationResponse callWeatherApi(double latitude, double longitude) {
		ResponseEntity<Object> response = HttpClientUtil.get(null, openWeatherConfig.getOpenWeatherUrl()
			+ "?lat=" + latitude
			+ "&lon=" + longitude
			+ "&appid=" + openWeatherConfig.getOpenWeatherApiKey()+ "&units=metric");
		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			return convertObject(response.getBody(), WeatherInformationResponse.class);
		} else {
			log.error("Failed to call Open Weather API: " + response.getStatusCode());
			throw new ExternalApiFailedException("Failed to call Open Weather API: " + response.getStatusCode());
		}
	}
}
