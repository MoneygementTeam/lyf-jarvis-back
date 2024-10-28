package com.moneygement.lyf.jarvis.recommendation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moneygement.lyf.jarvis.recommendation.domain.GroupRecommendation;

@Repository
public interface GroupRecommendationRepository extends JpaRepository<GroupRecommendation, Long> {
}
