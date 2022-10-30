package com.jeeyulee.mongddang.artscenter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArtsCenterResponseDTO {
    private Long dropsId;
    private String dropsName;
    private String memberId;
    private String nickname;
    private String profileUrl;
    private String painingName;
    private String genreName;
    private String description;
    private String paintingUrl;
    private Integer paintingMongddangCount;
}
