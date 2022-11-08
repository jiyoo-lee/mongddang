package com.jeeyulee.mongddang.drops.domain;

import com.jeeyulee.mongddang.painting.domain.PaintingDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DropsDetailDTO {
    private String memberId;
    private String nickname;
    private String dropsName;
    private String dropsGenre;
    private String dropsType;
    private String createDateTime;
    private List<PaintingDetailDTO> paintings;
}
