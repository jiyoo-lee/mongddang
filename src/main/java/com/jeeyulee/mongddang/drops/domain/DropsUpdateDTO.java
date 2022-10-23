package com.jeeyulee.mongddang.drops.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DropsUpdateDTO {
    private Long genreId;
    private String name;
    private Long typeId;
}
