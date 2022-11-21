package com.jeeyulee.mongddang.artscenter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArtsCenterPaintingDetailDTO {

    private Long paintingId;
    private String memberId;
    private String nickname;
    private String profileUrl;
    private String title;
    private String contestPaintingUrl;
    private String description;
    private Integer mongddangCount;
    private Boolean isLike;
    private Integer comment;
    private String createDatetime;
}
