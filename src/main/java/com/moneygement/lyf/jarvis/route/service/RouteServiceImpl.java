package com.moneygement.lyf.jarvis.route.service;

import static com.moneygement.lyf.jarvis.common.util.JsonUtil.convertObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneygement.lyf.jarvis.common.util.HttpClientUtil;
import com.moneygement.lyf.jarvis.route.config.GraphHopperConfig;
import com.moneygement.lyf.jarvis.route.domain.OptimalRouteRequest;
import com.moneygement.lyf.jarvis.route.domain.OptimalRouteResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    private final GraphHopperConfig graphHopperConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OptimalRouteResponse callGraphHopperForOptimalRoute(OptimalRouteRequest request) throws IOException {
        ResponseEntity<Object> response = HttpClientUtil.post(null, graphHopperConfig.getGraphHopperUrl()
                                                                    + "?key=" + graphHopperConfig.getGraphHopperApiKey(), request);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return convertObject(response.getBody(), OptimalRouteResponse.class);
        } else {
            log.error("Failed to call GraphHopper API: " + response.getStatusCode());
            throw new IOException("Failed to call GraphHopper API: " + response.getStatusCode());
        }
    }
}
