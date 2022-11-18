package com.jeeyulee.mongddang.painting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaintingDetailDTO {
    private String paintingId;
    private String genre;
    private String name;
    private String paintingUrl;
    private String description;
    private Integer mongddangCount;
    private Integer comment;
    private String createDatetime;

}
