package com.jeeyulee.mongddang.painting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaintingDTO {

    private Long dropsId;
    private Long genreId;
    private String name;
    private String extension;
    private String description;
}
