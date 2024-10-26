package com.moneygement.lyf.jarvis.ai.domain;

import com.moneygement.lyf.jarvis.travel.domain.Location;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptimalRouteResponse {
    private List<Location> optimalRoute; // 최적 경로의 경유지 리스트
}
