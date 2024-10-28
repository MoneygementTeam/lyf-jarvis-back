package com.moneygement.lyf.jarvis.recommendation.service;

import static com.moneygement.lyf.jarvis.common.util.JsonUtil.*;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moneygement.lyf.jarvis.common.util.HttpClientUtil;
import com.moneygement.lyf.jarvis.recommendation.config.RAGConfig;
import com.moneygement.lyf.jarvis.recommendation.domain.RagResponse;
import com.moneygement.lyf.jarvis.recommendation.domain.RecommendationCreateRAGRequest;
import com.moneygement.lyf.jarvis.route.domain.OptimalRouteRequest;
import com.moneygement.lyf.jarvis.route.domain.OptimalRouteResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RAGService {
	private final RAGConfig ragConfig;

	public RagResponse getRecommendations(RecommendationCreateRAGRequest request) throws IOException {
		ResponseEntity<Object> response = HttpClientUtil.post(null, ragConfig.getRagUrl(), request);
		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			return convertObject(response.getBody(), RagResponse.class);
		} else {
			log.error("Failed to call RAG API: " + response.getStatusCode());
			throw new IOException("Failed to call RAG API: " + response.getStatusCode());
		}
	}

}
