package com.jeeyulee.mongddang.artscenter.contestpainting.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContestPaintingDTO {

    private Long contestId;
    private String memberId;
    private String title;
    private String extension;
    private String description;

}
