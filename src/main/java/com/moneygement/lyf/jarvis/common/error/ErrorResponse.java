package com.moneygement.lyf.jarvis.common.error;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String errorMessage,HttpStatus status) {
}
