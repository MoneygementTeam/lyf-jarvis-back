package com.moneygement.lyf.jarvis.recommendation.domain;

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
public class RagResponse {

	@JsonProperty("startPoint")
	private Point startPoint;  // 출발지 정보

	@JsonProperty("endPoint")
	private Point endPoint;  // 도착지 정보

	@JsonProperty("pointList")
	private List<Point> pointList;  // 추천 명소 리스트

	@JsonProperty("message")
	private String message;  // 전체 메시지

	/**
	 * Point 클래스: 출발지, 도착지, 명소 정보를 담는 내부 클래스
	 */
	@Getter
	@Setter
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Point {

		@JsonProperty("name")
		private String name;  // 장소 이름

		@JsonProperty("latitude")
		private double latitude;  // 위도

		@JsonProperty("longitude")
		private double longitude;  // 경도

		@JsonProperty("message")
		private String message;  // 장소 설명 (명소일 경우)

		@JsonProperty("status")
		private String status;  // 장소 상태 (예: 맛집, 관광 등)
	}
}
