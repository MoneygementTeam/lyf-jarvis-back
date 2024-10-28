package com.moneygement.lyf.jarvis.history.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneygement.lyf.jarvis.history.domain.History;
import com.moneygement.lyf.jarvis.history.domain.HistoryCreateRequest;
import com.moneygement.lyf.jarvis.history.domain.HistoryResponse;
import com.moneygement.lyf.jarvis.history.service.HistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
@Tag(name = "History", description = "추천 목록 정보 조회 AP")
public class HistoryController {

	private final HistoryService historyService;

	@GetMapping("/{userId}")
	@Operation(summary = "History 조회", description = "UserId 기준의 History 정보를 제공 합니다.")
	public List<HistoryResponse> getHistoryByUserId(@PathVariable String userId) {
		return historyService.findByUserId(userId);
	}

	@PostMapping
	@Operation(summary = "History 등록", description = "History를 저장하는 기능을 제공합니다.")
	public ResponseEntity<History> createHistory(@RequestBody HistoryCreateRequest request) {
		History savedHistory = historyService.save(request);
		return new ResponseEntity<>(savedHistory, HttpStatus.CREATED);
	}
}
