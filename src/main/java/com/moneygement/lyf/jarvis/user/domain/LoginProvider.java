package com.moneygement.lyf.jarvis.user.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LoginProvider {

	KAKAO("kakao"),
	GOOGLE("google"),
	GITHUB("github");

	private final String providerName;


	public static LoginProvider fromString(String providerName) {
		for (LoginProvider provider : LoginProvider.values()) {
			if (provider.providerName.equalsIgnoreCase(providerName)) {
				return provider;
			}
		}
		throw new IllegalArgumentException("Unknown provider: " + providerName);
	}
}
