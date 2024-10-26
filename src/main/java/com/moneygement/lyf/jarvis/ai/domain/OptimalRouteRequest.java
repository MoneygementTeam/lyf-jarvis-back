package com.moneygement.lyf.jarvis.ai.domain;

import com.moneygement.lyf.jarvis.travel.domain.Location;
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
    private Location startPoint;      // 출발지
    private List<Location> pointList; // 목적지 리스트
}
