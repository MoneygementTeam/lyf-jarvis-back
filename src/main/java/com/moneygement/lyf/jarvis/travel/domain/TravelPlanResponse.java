package com.moneygement.lyf.jarvis.travel.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelPlanResponse {
    private Location startPoint;     // 출발지 좌표
    private List<Location> pointList;    // 목적지 좌표
    private List<Location> optimalRoute;   // 최단 경로
    private String ttsUrl;         // TTS mp4 파일 S3 URL
}
