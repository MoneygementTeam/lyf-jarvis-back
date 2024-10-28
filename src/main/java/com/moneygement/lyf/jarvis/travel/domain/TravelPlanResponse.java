package com.moneygement.lyf.jarvis.travel.domain;

import com.moneygement.lyf.jarvis.route.domain.OptimalRouteResponse;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public record TravelPlanResponse(
    Location startPoint,         // 출발지 좌표
    List<Location> pointList,      // 목적지 좌표
    OptimalRouteResponse optimalRoute  // 최단 경로
) {

}
