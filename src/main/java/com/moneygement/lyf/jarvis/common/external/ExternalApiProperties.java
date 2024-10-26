package com.moneygement.lyf.jarvis.common.external;


import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix = "external.api")
public class ExternalApiProperties {

	private final Kakao kakao;

	public ExternalApiProperties(Kakao kakao) {
		this.kakao = kakao;
	}

	public record Kakao(String baseUri, String redirectUri, String apiKey, String scheme, String clientSecret) { }

}
