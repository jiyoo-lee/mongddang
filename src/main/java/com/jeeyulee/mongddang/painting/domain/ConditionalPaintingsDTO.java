package com.jeeyulee.mongddang.painting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConditionalPaintingsDTO {
    private String memberId;
    private String nickname;
    private String profileUrl;
    private Long paintingId;
    private String genreName;
    private String name;
    private String paintingUrl;
    private String description;
    private Integer mongddangCount;

}
