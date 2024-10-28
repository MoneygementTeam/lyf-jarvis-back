package com.moneygement.lyf.jarvis.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneygement.lyf.jarvis.ai.domain.SmallChatRequest;
import com.moneygement.lyf.jarvis.ai.service.AiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@Tag(name = "Ai", description = "Ai 호출 API")
public class AiController {
	private final AiService aiService;

	@PostMapping("/small-chat")
	@Operation(summary = "잡담", description = "친절한 잡담을 제공합니다.")
	public ResponseEntity<String> recommendTravelPlan(@RequestBody SmallChatRequest smallChatRequest) {
		return ResponseEntity.ok(aiService.callSmallChat(smallChatRequest).chat());
	}


}
