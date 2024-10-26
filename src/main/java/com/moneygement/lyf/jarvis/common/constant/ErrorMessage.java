package com.moneygement.lyf.jarvis.common.constant;

import lombok.Getter;

@Getter
public enum ErrorMessage {
	COMMON_NOT_FOUND("해당 %s를 찾지 못했습니다"),
	COMMON_NOT_FOUND_WITH_TARGET("해당 %s를 찾지 못했습니다 : %s");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String format(Object... args) {
		return String.format(this.message, args);
	}
}
