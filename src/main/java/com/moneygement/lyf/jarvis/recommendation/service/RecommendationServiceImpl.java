package com.moneygement.lyf.jarvis.recommendation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moneygement.lyf.jarvis.recommendation.domain.Recommendation;
import com.moneygement.lyf.jarvis.recommendation.persistence.RecommendationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService{
	private final RecommendationRepository recommendationRepository;

	public List<Recommendation> getRecommendationsByGroupId(Long groupId) {
		return recommendationRepository.findByGroupId(groupId);
	}

}
