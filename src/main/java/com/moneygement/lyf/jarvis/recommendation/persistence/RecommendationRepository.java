package com.moneygement.lyf.jarvis.recommendation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.moneygement.lyf.jarvis.recommendation.domain.Recommendation;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
	List<Recommendation> findByGroupId(Long groupId);
}