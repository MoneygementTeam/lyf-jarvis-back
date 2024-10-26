package com.moneygement.lyf.jarvis.route.service;

import com.moneygement.lyf.jarvis.route.domain.OptimalRouteRequest;
import com.moneygement.lyf.jarvis.route.domain.OptimalRouteResponse;
import java.io.IOException;

public interface RouteService {
    OptimalRouteResponse callGraphHopperForOptimalRoute(OptimalRouteRequest request) throws IOException;
}
