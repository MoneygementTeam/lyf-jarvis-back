package com.moneygement.lyf.jarvis.weather.service;

import com.moneygement.lyf.jarvis.weather.domain.WeatherInformationResponse;

public interface WeatherService {
	WeatherInformationResponse getWeather(double latitude, double longitude);
}
