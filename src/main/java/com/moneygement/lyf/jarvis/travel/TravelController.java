package com.moneygement.lyf.jarvis.travel;

import com.moneygement.lyf.jarvis.travel.domain.TravelPlanRequest;
import com.moneygement.lyf.jarvis.travel.domain.TravelPlanResponse;
import com.moneygement.lyf.jarvis.travel.service.TravelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/travel")
@RequiredArgsConstructor
@Tag(name = "Travel", description = "여행 계획 추천 API")
public class TravelController {

    private final TravelService travelService;

    @PostMapping("/recommend")
    @Operation(summary = "여행 계획 추천", description = "출발지 좌표와 음성 인식 텍스트를 기반으로 AI가 추천하는 여행 계획을 제공합니다.")
    public ResponseEntity<TravelPlanResponse> recommendTravelPlan(@RequestBody TravelPlanRequest travelPlanRequest)
        throws IOException {
        TravelPlanResponse travelPlanResponse = travelService.createTravelPlan(travelPlanRequest);
        return ResponseEntity.ok(travelPlanResponse);
    }
}
