package com.jeeyulee.mongddang.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContestUpdateBuilderDTO {
    private Long contestId;
    private String title;
    private String contestPaintingUrl;
    private String startDay;
    private String endDay;
}
