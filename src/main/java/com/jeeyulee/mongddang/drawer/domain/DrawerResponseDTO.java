package com.jeeyulee.mongddang.drawer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DrawerResponseDTO {
    private Integer paintingCount;
    private List<DrawerGenreCountDTO> paintingCountGroupingGenre;
    private List<DrawerDropsDTO> drops;
}
