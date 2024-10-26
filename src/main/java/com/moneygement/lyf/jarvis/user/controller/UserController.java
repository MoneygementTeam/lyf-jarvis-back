package com.moneygement.lyf.jarvis.user.controller;

import com.moneygement.lyf.jarvis.common.external.ExternalApiProperties;
import com.moneygement.lyf.jarvis.common.external.OAuth2Service;
import com.moneygement.lyf.jarvis.common.external.dto.OauthUserInfoResponse;
import com.moneygement.lyf.jarvis.user.domain.LoginProvider;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "사용자 관리를 위한 API")
public class UserController {

    private final ExternalApiProperties externalApiProperties;
    private final ObjectMapper objectMapper;
    private final OAuth2Service oAuth2Service;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login/oauth2")
    public String login(Model model) {
        model.addAttribute("location", buildKakaoOAuthUrl());
        return "login";
    }

    @GetMapping("/login/oauth2/code")
    public String getAuthCode(@RequestParam String code,
                              @RequestParam(defaultValue = "kakao") String provider,
                              Model model) throws Exception{
        OauthUserInfoResponse userInfo = oAuth2Service.processOAuth2User(LoginProvider.fromString(provider), code);
        String userInfoJson = objectMapper.writeValueAsString(userInfo);
        model.addAttribute("userInfoJson", userInfoJson);
        return "userInfo";
    }

    private String buildKakaoOAuthUrl() {
        return String.format(
            "%s/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s",
            externalApiProperties.getKakao().baseUri(),
            externalApiProperties.getKakao().apiKey(),
            externalApiProperties.getKakao().redirectUri()
        );
    }
}
