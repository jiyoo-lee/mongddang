package com.jeeyulee.mongddang.artscenter.contestpainting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContestPaintingDTO {
    private Long paintingId;
    private Long contestId;
    private String memberId;
    private String title;
    private String contestPaintingUrl;
    private Integer comment;
    private Integer isLike;
    private String description;

}
