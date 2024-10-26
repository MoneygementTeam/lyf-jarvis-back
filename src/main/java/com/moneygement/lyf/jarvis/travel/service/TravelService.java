package com.moneygement.lyf.jarvis.travel.service;

import com.moneygement.lyf.jarvis.travel.domain.TravelPlanRequest;
import com.moneygement.lyf.jarvis.travel.domain.TravelPlanResponse;
import java.io.IOException;

public interface TravelService {
    TravelPlanResponse createTravelPlan(TravelPlanRequest request) throws IOException;
}
