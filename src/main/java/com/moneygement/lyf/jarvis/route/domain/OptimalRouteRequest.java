package com.moneygement.lyf.jarvis.route.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OptimalRouteRequest {

    private List<List<Double>> points; // 경로를 나타내는 좌표 리스트

    // 가능한 옵션: "motorway" (고속도로), "ferry" (페리), "tunnel" (터널), "bridge" (다리), "track" (비포장 도로 등).
    @JsonProperty("snap_preventions")
    private List<String> snapPreventions = List.of("motorway", "ferry", "tunnel"); // 경로 생성 시 스냅을 피할 도로 유형 목록

    // 가능한 옵션: "road_class" (도로 종류), "surface" (도로 표면 상태), "max_speed" (최고 속도), "lane_count" (차선 수).
    private List<String> details = List.of("road_class", "surface"); // 응답에 추가할 세부 정보 목록

    // 가능한 옵션: "car" (자동차), "bike" (자전거), "foot" (도보), "truck" (트럭, 대형 차량).
    private String profile = "car"; // 경로 계산에 사용할 이동 수단의 프로필

    // 가능한 옵션: "en" (영어), "de" (독일어), "fr" (프랑스어), "es" (스페인어) 등.
    private String locale = "en"; // 응답의 언어 설정

    private boolean instructions = true; // 길 안내 지침을 포함할지 여부

    @JsonProperty("calc_points")
    private boolean calcPoints = true; // 경로의 점을 계산할지 여부

    @JsonProperty("points_encoded")
    private boolean pointsEncoded = false; // 응답의 경로 점을 인코딩할지 여부

    public OptimalRouteRequest(List<List<Double>> points) {
        this.points = points;
    }
}
