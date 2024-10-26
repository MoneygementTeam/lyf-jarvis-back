package com.moneygement.lyf.jarvis.user.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserType {
	REGULAR("regular"), // 일반 사용자
	SOCIAL("social");   // 소셜 로그인 사용자

	private final String userType;
}
