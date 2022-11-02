package com.jeeyulee.mongddang.artscenter.contestpainting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContestPaintingDeleteDTO {

    private Long contestPaintingId;
    private String memberId;
}
