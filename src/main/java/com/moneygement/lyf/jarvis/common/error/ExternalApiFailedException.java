package com.moneygement.lyf.jarvis.common.error;

public class ExternalApiFailedException extends RuntimeException {
	public ExternalApiFailedException() {
	}

	public ExternalApiFailedException(String message) {
		super(message);
	}
}
