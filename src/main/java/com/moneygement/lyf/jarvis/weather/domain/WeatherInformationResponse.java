package com.moneygement.lyf.jarvis.weather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInformationResponse {

	@JsonProperty("coord")
	private Coord coord;                        // 좌표 정보

	@JsonProperty("weather")
	private List<Weather> weather;              // 날씨 정보 리스트

	@JsonProperty("base")
	private String base;                        // 기상 관측소 정보

	@JsonProperty("main")
	private Main main;                          // 주요 기상 데이터

	@JsonProperty("visibility")
	private int visibility;                     // 가시 거리 (미터)

	@JsonProperty("wind")
	private Wind wind;                          // 풍속 정보

	@JsonProperty("rain")
	private Rain rain;                          // 비 정보

	@JsonProperty("clouds")
	private Clouds clouds;                      // 구름 정보

	@JsonProperty("dt")
	private long dt;                            // 데이터 타임스탬프

	@JsonProperty("sys")
	private Sys sys;                            // 시스템 정보

	@JsonProperty("timezone")
	private int timezone;                       // 타임존 (초)

	@JsonProperty("id")
	private int id;                             // 도시 ID

	@JsonProperty("name")
	private String name;                        // 도시 이름

	@JsonProperty("cod")
	private int cod;                            // 응답 코드

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Coord {
		@JsonProperty("lon")
		private double lon;                     // 경도
		@JsonProperty("lat")
		private double lat;                     // 위도
	}

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Weather {
		@JsonProperty("id")
		private int id;                         // 날씨 코드
		@JsonProperty("main")
		private String main;                    // 주요 날씨
		@JsonProperty("description")
		private String description;             // 날씨 설명
		@JsonProperty("icon")
		private String icon;                    // 날씨 아이콘 ID
	}

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Main {
		@JsonProperty("temp")
		private double temp;                    // 현재 온도 (섭씨)
		@JsonProperty("feels_like")
		private double feelsLike;               // 체감 온도
		@JsonProperty("temp_min")
		private double tempMin;                 // 최저 온도
		@JsonProperty("temp_max")
		private double tempMax;                 // 최고 온도
		@JsonProperty("pressure")
		private int pressure;                   // 대기압 (hPa)
		@JsonProperty("humidity")
		private int humidity;                   // 습도 (%)
		@JsonProperty("sea_level")
		private int seaLevel;                   // 해수면 대기압
		@JsonProperty("grnd_level")
		private int grndLevel;                  // 지면 대기압
	}

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Wind {
		@JsonProperty("speed")
		private double speed;                   // 풍속 (m/s)
		@JsonProperty("deg")
		private int deg;                        // 풍향 (도)
	}

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Rain {
		@JsonProperty("1h")
		private double oneHour;                 // 지난 1시간 동안 내린 비의 양 (mm)
	}

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Clouds {
		@JsonProperty("all")
		private int all;                        // 구름량 (%)
	}

	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Sys {
		@JsonProperty("type")
		private int type;                       // 시스템 유형
		@JsonProperty("id")
		private int id;                         // 시스템 ID
		@JsonProperty("country")
		private String country;                 // 국가 코드
		@JsonProperty("sunrise")
		private long sunrise;                   // 일출 시간 (Unix 타임스탬프)
		@JsonProperty("sunset")
		private long sunset;                    // 일몰 시간 (Unix 타임스탬프)
	}
}
