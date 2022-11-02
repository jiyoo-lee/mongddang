package com.jeeyulee.mongddang.artscenter.contestpainting.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContestPaintingBuilderDTO {

    private String memberId;
    private Long contestId;
    private String title;
    private String contestPaintingUrl;
    private String description;
}
