package com.jeeyulee.mongddang.drawer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DrawerDropsDTO {
    private Long dropsId;
    private String dropsName;
    private String dropsGenre;
    private String lastPaintingUrl;
    private Boolean isLike;
    private Integer mongddangCount;
}
