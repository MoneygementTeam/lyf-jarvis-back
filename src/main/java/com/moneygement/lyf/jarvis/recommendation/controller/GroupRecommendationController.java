package com.moneygement.lyf.jarvis.recommendation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneygement.lyf.jarvis.recommendation.domain.Recommendation;
import com.moneygement.lyf.jarvis.recommendation.service.RecommendationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
@Tag(name = "Recommend", description = "추천 관련 정보를 호출하고, 저장하고, 조회할 수 있습니다.")
public class GroupRecommendationController {
	private final RecommendationService recommendationService;

	@GetMapping("group/{groupId}")
	@Operation(summary = "History 조회", description = "GroupId 기준의 추천 장소 목록 정보를 제공 합니다.")
	public List<Recommendation> getRecommendationsByGroupId(@PathVariable Long groupId) {
		return recommendationService.getRecommendationsByGroupId(groupId);
	}

}
