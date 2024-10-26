package com.moneygement.lyf.jarvis.common.external.dto;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KakaoUserInfoResponse(
	@JsonProperty("id") Long id,
	@JsonProperty("nickname") String nickname,
	@JsonProperty("has_signed_up") Boolean hasSignedUp,
	@JsonProperty("connected_at") Date connectedAt,
	@JsonProperty("synched_at") Date synchedAt,
	@JsonProperty("properties") HashMap<String, String> properties,
	@JsonProperty("kakao_account") KakaoAccount kakaoAccount,
	@JsonProperty("for_partner") Partner partner
) {

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record KakaoAccount(
		@JsonProperty("profile_needs_agreement") Boolean isProfileAgree,
		@JsonProperty("profile_nickname_needs_agreement") Boolean isNickNameAgree,
		@JsonProperty("profile_image_needs_agreement") Boolean isProfileImageAgree,
		@JsonProperty("profile") Profile profile,
		@JsonProperty("name_needs_agreement") Boolean isNameAgree,
		@JsonProperty("name") String name,
		@JsonProperty("email_needs_agreement") Boolean isEmailAgree,
		@JsonProperty("is_email_valid") Boolean isEmailValid,
		@JsonProperty("is_email_verified") Boolean isEmailVerified,
		@JsonProperty("email") String email,
		@JsonProperty("age_range_needs_agreement") Boolean isAgeAgree,
		@JsonProperty("age_range") String ageRange,
		@JsonProperty("birthyear_needs_agreement") Boolean isBirthYearAgree,
		@JsonProperty("birthyear") String birthYear,
		@JsonProperty("birthday_needs_agreement") Boolean isBirthDayAgree,
		@JsonProperty("birthday") String birthDay,
		@JsonProperty("birthday_type") String birthDayType,
		@JsonProperty("gender_needs_agreement") Boolean isGenderAgree,
		@JsonProperty("gender") String gender,
		@JsonProperty("phone_number_needs_agreement") Boolean isPhoneNumberAgree,
		@JsonProperty("phone_number") String phoneNumber,
		@JsonProperty("ci_needs_agreement") Boolean isCIAgree,
		@JsonProperty("ci") String ci,
		@JsonProperty("ci_authenticated_at") Date ciCreatedAt
	) {

		@JsonIgnoreProperties(ignoreUnknown = true)
		public record Profile(
			@JsonProperty("nickname") String nickName,
			@JsonProperty("thumbnail_image_url") String thumbnailImageUrl,
			@JsonProperty("profile_image_url") String profileImageUrl,
			@JsonProperty("is_default_image") String isDefaultImage,
			@JsonProperty("is_default_nickname") Boolean isDefaultNickName
		) {
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public record Partner(
		@JsonProperty("uuid") String uuid
	) {
	}
}
