package com.moneygement.lyf.jarvis.user.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserDto {
	private String userId;
	private String userPw;
}
