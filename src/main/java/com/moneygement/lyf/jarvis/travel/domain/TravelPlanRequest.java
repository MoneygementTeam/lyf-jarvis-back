package com.moneygement.lyf.jarvis.travel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelPlanRequest {
    private String startPoint;  // 출발지 좌표
    private String voiceText;   // 음성 텍스트
}
