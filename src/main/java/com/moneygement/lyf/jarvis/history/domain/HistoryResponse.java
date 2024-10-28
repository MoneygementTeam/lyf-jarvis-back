package com.moneygement.lyf.jarvis.history.domain;

import java.time.LocalDateTime;

public record HistoryResponse(String userId,
							  Long groupId,
							  String jarvisMessage,
							  String userMessage,
							  LocalDateTime createdDate) {
}
