package com.moneygement.lyf.jarvis.recommendation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneygement.lyf.jarvis.common.error.ExternalApiFailedException;
import com.moneygement.lyf.jarvis.history.domain.HistoryCreateRequest;
import com.moneygement.lyf.jarvis.history.service.HistoryService;
import com.moneygement.lyf.jarvis.recommendation.domain.GroupRecommendation;
import com.moneygement.lyf.jarvis.recommendation.domain.RagResponse;
import com.moneygement.lyf.jarvis.recommendation.domain.Recommendation;
import com.moneygement.lyf.jarvis.recommendation.domain.RecommendationCreateRAGRequest;
import com.moneygement.lyf.jarvis.recommendation.domain.RecommendationCreateRequest;
import com.moneygement.lyf.jarvis.recommendation.persistence.GroupRecommendationRepository;
import com.moneygement.lyf.jarvis.recommendation.persistence.RecommendationRepository;
import com.moneygement.lyf.jarvis.weather.service.WeatherReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
	private final GroupRecommendationRepository groupRecommendationRepository;
	private final RecommendationRepository recommendationRepository;
	private final HistoryService historyService;
	private final WeatherReportService weatherReportService;
	private final RAGService ragService;

	@Override
	public List<Recommendation> getRecommendationsByGroupId(Long groupId) {
		return recommendationRepository.findByGroupId(groupId);
	}

	@Override
	@Transactional
	public String recommend(RecommendationCreateRequest request) {
		// 1. weather service summary 서비스 호출 ->  weather 값에 포함
		String weather =
			weatherReportService.getWeatherSummary(request.latitude(), request.longitude());
		// 2. RAG 호출
		RagResponse ragResponse;
		try {
			ragResponse = ragService.getRecommendations(new RecommendationCreateRAGRequest(
				request.userId(),
				request.request(),
				request.latitude(),
				request.longitude(),
				weather
			));
		} catch (IOException e) {
			throw new ExternalApiFailedException(e.getMessage());
		}
		// 3. group_recommendation 저장
		if (ragResponse == null) {
			throw new ExternalApiFailedException("Can't retrieve data from RAG API");
		}

		GroupRecommendation group = new GroupRecommendation(ragResponse.getMessage(), request.userId());
		group.setSessionName("자동 생성된 여행 코스");
		GroupRecommendation savedGroup = groupRecommendationRepository.save(group);

		// 4. recommendation 저장
		recommendationRepository.save(new Recommendation(
			savedGroup.getGroupId(),
			ragResponse.getStartPoint().getName(),
			ragResponse.getMessage(),
			ragResponse.getStartPoint().getStatus(),
			ragResponse.getStartPoint().getLatitude(),
			ragResponse.getStartPoint().getLongitude()));

		for (RagResponse.Point point : ragResponse.getPointList()) {
			Recommendation recommendation = new Recommendation(
				savedGroup.getGroupId(),
				point.getName(),
				point.getMessage(),
				point.getStatus(),
				point.getLatitude(),
				point.getLongitude());
			recommendationRepository.save(recommendation);
		}

		// 5. history에 저장
		historyService.save(new HistoryCreateRequest(
			request.userId(),
			savedGroup.getGroupId(),
			request.request(),
			ragResponse.getMessage()));
		return ragResponse.getMessage();
	}
}
