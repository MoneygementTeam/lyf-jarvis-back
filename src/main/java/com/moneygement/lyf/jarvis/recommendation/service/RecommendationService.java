package com.moneygement.lyf.jarvis.recommendation.service;

import java.util.List;

import com.moneygement.lyf.jarvis.recommendation.domain.Recommendation;
import com.moneygement.lyf.jarvis.recommendation.domain.RecommendationCreateRequest;

public interface RecommendationService {
	List<Recommendation> getRecommendationsByGroupId(Long groupId);

	String recommend(RecommendationCreateRequest request);
}
