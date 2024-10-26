package com.moneygement.lyf.jarvis.travel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelPlanResponse {
    private String startPoint;     // 출발지 좌표
    private String[] pointList;    // 목적지 좌표
    private String optimalRoute;          // 최단 경로
    private String ttsUrl;         // TTS mp4 파일 S3 URL
}
