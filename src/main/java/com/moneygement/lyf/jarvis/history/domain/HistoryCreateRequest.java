package com.moneygement.lyf.jarvis.history.domain;

import jakarta.validation.constraints.NotNull;

public record HistoryCreateRequest(
	@NotNull(message = "user id should be not null") String userId,
	Long groupId,
	@NotNull(message = "request should be not null") String request,
	@NotNull(message = "response should be not null") String response
) {
}
