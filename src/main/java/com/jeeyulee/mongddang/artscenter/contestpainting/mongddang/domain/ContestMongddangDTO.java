package com.jeeyulee.mongddang.artscenter.contestpainting.mongddang.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContestMongddangDTO {

    private Long contestPaintingId;
    private String memberId;

}
