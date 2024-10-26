package com.moneygement.lyf.jarvis.ai.domain;

import com.moneygement.lyf.jarvis.travel.domain.Location;
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
public class VoiceTextParsingRequest {
    private Location startPoint; // 출발지
    private String voiceText;    // 음성 텍스트
}
