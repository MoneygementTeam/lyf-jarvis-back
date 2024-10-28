package com.moneygement.lyf.jarvis.history.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.moneygement.lyf.jarvis.history.domain.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

	// userId로 History 목록 조회
	List<History> findByUserId(String userId);
}