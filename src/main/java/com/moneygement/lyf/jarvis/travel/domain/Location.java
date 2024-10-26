package com.moneygement.lyf.jarvis.travel.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String name;      // 장소 이름
    private double latitude;  // 위도
    private double longitude; // 경도
}
