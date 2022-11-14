package com.jeeyulee.mongddang.painting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaintingDTO {

    private String memberId;
    private Long dropsId;
    private Long genreId;
    private String name;
    private String paintingUrl;
    private String description;
}
