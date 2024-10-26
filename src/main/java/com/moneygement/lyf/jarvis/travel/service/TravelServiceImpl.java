package com.moneygement.lyf.jarvis.travel.service;

import com.moneygement.lyf.jarvis.ai.domain.PointPickRequest;
import com.moneygement.lyf.jarvis.ai.domain.PointPickResponse;
import com.moneygement.lyf.jarvis.ai.domain.VoiceTextParsingRequest;
import com.moneygement.lyf.jarvis.ai.domain.VoiceTextParsingResponse;
import com.moneygement.lyf.jarvis.ai.service.AiService;
import com.moneygement.lyf.jarvis.route.service.RouteService;
import com.moneygement.lyf.jarvis.travel.domain.Location;
import com.moneygement.lyf.jarvis.route.domain.OptimalRouteRequest;
import com.moneygement.lyf.jarvis.route.domain.OptimalRouteResponse;
import com.moneygement.lyf.jarvis.travel.domain.TravelPlanRequest;
import com.moneygement.lyf.jarvis.travel.domain.TravelPlanResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {
    private final AiService aiService;
    private final RouteService routeService;

    @Override
    public TravelPlanResponse createTravelPlan(TravelPlanRequest request) throws IOException {
        // 1. Open AI API 호출하여 음성 텍스트를 자연스럽게 파싱 - 개떡같이 말해도 더 나은 명령으로
        VoiceTextParsingResponse voiceTextParsingResponse = aiService.callGptForVoiceTextParsing(new VoiceTextParsingRequest(request.getStartPoint(), request.getVoiceText()));

        // 2. RAG 추천 API 호출하여 여행 목적지 리스트 추천
        //TODO RAG 연동
//        PointPickResponse pointPickResponse = aiService.callRagForPointPick(new PointPickRequest(voiceTextParsingResponse.getParsedVoiceText()));
        PointPickResponse pointPickResponse = aiService.callGptForPointPick(new PointPickRequest(voiceTextParsingResponse.getParsedVoiceText()));

        // 3. Graph Hopper API 호출하여 최적 경로 요청
        List<List<Double>> points = pointPickResponse.getLocationList().stream()
                                                     .map(location -> List.of(location.getLongitude(), location.getLatitude()))
                                                     .toList();
        OptimalRouteRequest optimalRouteRequest = new OptimalRouteRequest(points);
        OptimalRouteResponse optimalRouteResponse = routeService.callGraphHopperForOptimalRoute(optimalRouteRequest);

        // 4. 여행 계획 DTO 생성
        return createTravelPlanResponse(request.getStartPoint(),
                                        pointPickResponse.getLocationList(),
                                        optimalRouteResponse);
    }

    private TravelPlanResponse createTravelPlanResponse(Location startPoint, List<Location> pointList, OptimalRouteResponse optimalRoute) {
        TravelPlanResponse response = new TravelPlanResponse();
        response.setStartPoint(startPoint);
        response.setPointList(pointList);
        response.setOptimalRoute(optimalRoute);
        return response;
    }
}
