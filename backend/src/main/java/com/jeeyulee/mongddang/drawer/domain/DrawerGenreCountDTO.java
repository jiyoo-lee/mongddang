package com.jeeyulee.mongddang.drawer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DrawerGenreCountDTO {
    private String genreName;
    private Integer count;
}
