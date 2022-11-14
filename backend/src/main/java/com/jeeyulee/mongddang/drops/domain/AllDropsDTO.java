package com.jeeyulee.mongddang.drops.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllDropsDTO {

    private Long dropsId;
    private String name;
    private String lastImage;
}
