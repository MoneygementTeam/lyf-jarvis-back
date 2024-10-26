package com.moneygement.lyf.jarvis.ai.service;

import com.moneygement.lyf.jarvis.ai.domain.OptimalRouteRequest;
import com.moneygement.lyf.jarvis.ai.domain.OptimalRouteResponse;
import com.moneygement.lyf.jarvis.ai.domain.PointPickRequest;
import com.moneygement.lyf.jarvis.ai.domain.PointPickResponse;
import com.moneygement.lyf.jarvis.ai.domain.VoiceTextParsingRequest;
import com.moneygement.lyf.jarvis.ai.domain.VoiceTextParsingResponse;

public interface AiService {
    VoiceTextParsingResponse callGptForVoiceTextParsing(VoiceTextParsingRequest request);
    OptimalRouteResponse callGptForOptimalRoute(OptimalRouteRequest request);
    PointPickResponse callGptForPointPick(PointPickRequest request);
    PointPickResponse callRagForPointPick(PointPickRequest request);
}
