package com.moneygement.lyf.jarvis.travel.service;

import com.moneygement.lyf.jarvis.travel.domain.TravelPlanRequest;
import com.moneygement.lyf.jarvis.travel.domain.TravelPlanResponse;

public interface TravelService {
    /**
     * 여행 계획 생성
     * @param request 여행 계획 요청 정보 (출발지 좌표, 음성 텍스트 등)
     * @return 최종 여행 계획 응답
     */
    TravelPlanResponse createTravelPlan(TravelPlanRequest request);
}
