package com.jeeyulee.mongddang.drops.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DropsDTO {

    private Long id;
    private String userId;
    private Long genreId;
    private String name;
    private Long typeId;
}
