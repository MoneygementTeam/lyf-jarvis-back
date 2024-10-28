package com.moneygement.lyf.jarvis.recommendation.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecommendationCreateRAGRequest(@NotBlank(message = "user id can't be empty") String userId,
											 @NotBlank(message = "command can't be empty") String command,
											 @NotNull(message = "latitude can't be empty") Double latitude,
											 @NotNull(message = "latitude can't be empty") Double longitude,
											 String weather
											 ) {
}
