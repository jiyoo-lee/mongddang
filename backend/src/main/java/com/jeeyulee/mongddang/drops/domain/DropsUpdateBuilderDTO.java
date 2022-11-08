package com.jeeyulee.mongddang.drops.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DropsUpdateBuilderDTO {
    private Long dropsId;
    private Long genreId;
    private String name;
    private Long typeId;
}
