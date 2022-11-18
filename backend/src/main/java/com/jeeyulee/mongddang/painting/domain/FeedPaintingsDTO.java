package com.jeeyulee.mongddang.painting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedPaintingsDTO {
    private String memberId;
    private String nickname;
    private String profileUrl;
    private Long paintingId;
    private String genreName;
    private String name;
    private String paintingUrl;
    private String description;
    private String createDatetime;
    private String comment;
    private Boolean isLike;
    private Integer mongddangCount;
}
