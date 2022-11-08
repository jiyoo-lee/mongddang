package com.jeeyulee.mongddang.artscenter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArtsCenterResponseDTO {
    private String contestId;
    private String title;
    private String posterUrl;
    private String startDay;
    private String endDay;
    private String memberId;
    private String nickname;
    private Long PaintingId;
    private String paintingTitle;
    private String paintingUrl;
    private Integer mongddangCount;
}
