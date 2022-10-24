package com.jeeyulee.mongddang.painting.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaintingUpdateDTO {

    private Long genreId;
    private String name;
    private String extension;
    private String description;
}
