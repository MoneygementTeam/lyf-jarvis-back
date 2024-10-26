package com.moneygement.lyf.jarvis.route.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
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
@JsonIgnoreProperties(ignoreUnknown = true) // 예기치 않은 필드 무시
public class OptimalRouteResponse {

    @JsonProperty("hints")
    private Hints hints;                         // 경로 탐색 과정에서의 힌트 정보
    @JsonProperty("info")
    private Info info;                           // 응답 정보와 관련된 메타데이터
    @JsonProperty("paths")
    private List<Path> paths;                    // 경로 정보 리스트

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Hints {
        @JsonProperty("visited_nodes.sum")
        private int visitedNodesSum;             // 방문한 노드의 총합
        @JsonProperty("visited_nodes.average")
        private int visitedNodesAverage;         // 방문한 노드의 평균
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Info {
        @JsonProperty("copyrights")
        private List<String> copyrights;         // 저작권 정보
        @JsonProperty("took")
        private int took;                        // 요청 처리 시간 (ms)
        @JsonProperty("road_data_timestamp")
        private String roadDataTimestamp;        // 도로 데이터의 타임스탬프
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Path {
        @JsonProperty("distance")
        private double distance;                 // 경로의 총 거리 (m)
        @JsonProperty("weight")
        private double weight;                   // 경로의 가중치
        @JsonProperty("time")
        private long time;                       // 경로의 예상 시간 (ms)
        @JsonProperty("transfers")
        private int transfers;                   // 경로 내 환승 횟수
        @JsonProperty("points_encoded")
        private boolean pointsEncoded;           // 경로 점이 인코딩되었는지 여부
        @JsonProperty("bbox")
        private List<Double> bbox;               // 경로 경계 박스
        @JsonProperty("points")
        private LineString points;                   // 경로의 좌표 정보
        @JsonProperty("instructions")
        private List<Instruction> instructions;  // 경로 지침 리스트
        @JsonProperty("legs")
        private List<Leg> legs;                  // 경로 구간 리스트 (비어 있음)
        @JsonProperty("details")
        private Map<String, List<Object>> details;  // 도로 표면, 클래스 등의 세부 정보
        @JsonProperty("ascend")
        private double ascend;                   // 경로의 총 상승 고도
        @JsonProperty("descend")
        private double descend;                  // 경로의 총 하강 고도
        @JsonProperty("snapped_waypoints")
        private LineString snappedWaypoints;         // 스냅된 경로의 좌표 정보
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LineString {
        private String type;
        private List<List<Double>> coordinates;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Instruction {
        @JsonProperty("distance")
        private double distance;                 // 지침에 따른 거리
        @JsonProperty("heading")
        private Double heading;                  // 지침의 방향
        @JsonProperty("last_heading")
        private Double lastHeading;              // 이전 지침의 방향
        @JsonProperty("sign")
        private int sign;                        // 방향 지시 신호
        @JsonProperty("interval")
        private List<Integer> interval;          // 구간 인덱스
        @JsonProperty("text")
        private String text;                     // 지침 내용
        @JsonProperty("time")
        private long time;                       // 지침에 따른 예상 시간 (ms)
        @JsonProperty("street_name")
        private String streetName;               // 도로 이름
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Leg {
        // 비어 있는 객체
    }
}
