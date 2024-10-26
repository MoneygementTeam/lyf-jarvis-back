package com.moneygement.lyf.jarvis.common.external;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.moneygement.lyf.jarvis.common.external.dto.KakaoTokenResponse;
import com.moneygement.lyf.jarvis.common.external.dto.KakaoUserInfoResponse;
import com.moneygement.lyf.jarvis.common.external.dto.OauthUserInfoResponse;
import com.moneygement.lyf.jarvis.user.domain.LoginProvider;
import com.moneygement.lyf.jarvis.user.domain.User;
import com.moneygement.lyf.jarvis.user.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2Service {
	private final UserRepository userRepository;
	private final ExternalApiProperties externalApiProperties;

	public OauthUserInfoResponse processOAuth2User(LoginProvider provider, String code) {
		if (provider == LoginProvider.KAKAO) {
			return processKakaoLogin(code);
		} else if (provider == LoginProvider.GOOGLE) {
			return processGoogleLogin(code);
		} else {
			throw new IllegalArgumentException("Not Supported Login Provider");
		}
	}

	// TODO 구글 로그인 처리 추가
	private OauthUserInfoResponse processGoogleLogin(String code) {
		throw new IllegalStateException("Not Supported Login Provider");
	}

	private OauthUserInfoResponse processKakaoLogin(String code) {

		WebClient webClient = WebClient.create(externalApiProperties.getKakao().baseUri());

		KakaoTokenResponse kakaoTokenResponse = webClient.post()
			.uri("/oauth/token")
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
			.body(BodyInserters.fromFormData("grant_type", "authorization_code")
				.with("client_id", externalApiProperties.getKakao().apiKey())
				.with("redirect_uri", externalApiProperties.getKakao().redirectUri())
				.with("code", code)
				.with("client_secret", externalApiProperties.getKakao().clientSecret())
			)
			.retrieve()
			.bodyToMono(KakaoTokenResponse.class)
			.block();

		assert kakaoTokenResponse != null;
		log.info("Access Token : {}", kakaoTokenResponse.accessToken());
		log.info("Refresh Token : {}", kakaoTokenResponse.refreshToken());

		KakaoUserInfoResponse kakaoUserInfoResponse = requestKakaoUserInfo(kakaoTokenResponse.accessToken());
		log.info("User Info : {}", kakaoUserInfoResponse);
		return saveUser(kakaoUserInfoResponse, kakaoTokenResponse);

	}

	private KakaoUserInfoResponse requestKakaoUserInfo(String accessToken) {
		WebClient webClient = WebClient.create("https://kapi.kakao.com");

		return webClient.get()
			.uri("/v2/user/me")
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
			.retrieve()
			.bodyToMono(KakaoUserInfoResponse.class)
			.block();
	}

	private OauthUserInfoResponse saveUser(KakaoUserInfoResponse userInfo, KakaoTokenResponse kakaoTokenResponse) {

		User user = userRepository.findUserByUserId(userInfo.kakaoAccount().email())
			.orElseGet(() -> User.createOauth(LoginProvider.KAKAO, userInfo.id().toString(),
				userInfo.kakaoAccount().profile().nickName(), kakaoTokenResponse.accessToken()));

		user.update(kakaoTokenResponse.accessToken(),
			kakaoTokenResponse.refreshToken());

		user = userRepository.save(user);

		return OauthUserInfoResponse.of(user.getUserId(), user.getNickname(), kakaoTokenResponse.accessToken(),
			kakaoTokenResponse.refreshToken());
	}

}
