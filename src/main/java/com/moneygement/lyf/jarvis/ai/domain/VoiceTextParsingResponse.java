package com.moneygement.lyf.jarvis.ai.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VoiceTextParsingResponse {
    private String parsedVoiceText; // 정제된 음성 텍스트
}
