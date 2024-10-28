package com.moneygement.lyf.jarvis.weather.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneygement.lyf.jarvis.weather.domain.WeatherInformationResponse;
import com.moneygement.lyf.jarvis.weather.service.WeatherReportService;
import com.moneygement.lyf.jarvis.weather.service.WeatherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Tag(name = "Weather", description = "날씨 정보 조회 AP")
public class WeatherController {
	private final WeatherService weatherService;
	private final WeatherReportService weatherReportService;

	@GetMapping("")
	@Operation(summary = "날씨 정보 조회", description = "좌표 기준(위,경도)의 날씨 정보를 제공 합니다.")
	public ResponseEntity<WeatherInformationResponse> recommendTravelPlan(@RequestParam double latitude,
		@RequestParam double longitude) {
		WeatherInformationResponse weatherInformationResponse = weatherService.getWeather(latitude,
			longitude);
		return ResponseEntity.ok(weatherInformationResponse);
	}

   @GetMapping("/summary")
   @Operation(summary = "날씨 정보 요약 조회", description = "좌표 기준(위,경도)의 날씨 요약을 제공 합니다.")
   public String getWeatherSummary(@RequestParam double latitude, @RequestParam double longitude) {
		return weatherReportService.getWeatherSummary(latitude, longitude);}

}
