package com.jeeyulee.mongddang.painting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaintingUpdateBuilderDTO {

    private Long paintingId;
    private Long genreId;
    private String name;
    private String paintingUrl;
    private String description;

}
