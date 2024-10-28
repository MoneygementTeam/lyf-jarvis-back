package com.moneygement.lyf.jarvis.history.service;

import java.util.List;

import com.moneygement.lyf.jarvis.history.domain.History;
import com.moneygement.lyf.jarvis.history.domain.HistoryCreateRequest;
import com.moneygement.lyf.jarvis.history.domain.HistoryResponse;

public interface HistoryService {

	History save(HistoryCreateRequest historyCreateRequest);

	List<HistoryResponse> findByUserId(String userId);
}
