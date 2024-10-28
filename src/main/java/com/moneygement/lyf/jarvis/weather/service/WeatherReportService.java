package com.moneygement.lyf.jarvis.weather.service;

import org.springframework.stereotype.Service;

import com.moneygement.lyf.jarvis.weather.domain.WeatherInformationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherReportService {
	private final WeatherService weatherService;

	public String getWeatherSummary(double latitude, double longitude) {
		WeatherInformationResponse response = weatherService.getWeather(latitude, longitude);

		return String.format(
			"In %s: %s, %.1f°C (feels like %.1f°C)",
			response.getName(),
			response.getWeather().getFirst().getMain(),
			response.getMain().getTemp(),
			response.getMain().getFeelsLike()
		);
	}
}