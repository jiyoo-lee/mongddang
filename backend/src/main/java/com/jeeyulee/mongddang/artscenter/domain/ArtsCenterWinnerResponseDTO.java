package com.jeeyulee.mongddang.artscenter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArtsCenterWinnerResponseDTO {
    private String memberId;
    private String nickname;
    private String profileUrl;
    private String paintingId;
    private String paintingName;
    private String description;
    private String paintingUrl;
    private Integer paintingMongddangCount;
}
