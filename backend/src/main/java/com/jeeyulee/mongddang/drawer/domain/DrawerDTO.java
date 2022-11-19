package com.jeeyulee.mongddang.drawer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DrawerDTO {
    private Long id;
    private String lastPaintingUrl;
    private String name;
    private String createDatetime;
    private String genre;
    private Boolean isLike;
}
