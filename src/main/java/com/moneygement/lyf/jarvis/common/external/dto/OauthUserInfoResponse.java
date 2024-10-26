package com.moneygement.lyf.jarvis.common.external.dto;

public record OauthUserInfoResponse(String userId, String nickname, String accessToken, String refreshToken) {

	public static OauthUserInfoResponse of(String userId, String nickname, String accessToken, String refreshToken) {
		return new OauthUserInfoResponse(userId, nickname, accessToken, refreshToken);
	}

}
