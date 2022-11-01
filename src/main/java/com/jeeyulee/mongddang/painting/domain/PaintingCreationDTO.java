package com.jeeyulee.mongddang.painting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaintingCreationDTO {

    private String memberId;
    private Long dropsId;
    private Long genreId;
    private String name;
    private String paintingUrl;
    private String description;
}
