package com.jeeyulee.mongddang.painting.service;

import com.jeeyulee.mongddang.painting.domain.PaintingDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateDTO;
import com.jeeyulee.mongddang.painting.domain.PopularPaintingsDTO;

import java.util.List;

public interface PaintingService {

    public String createPainting(PaintingDTO paintingDTO);

    public String updatePainting(Long paintingId, PaintingUpdateDTO paintingUpdateDTO);

    public Boolean deletePainting(Long paintingId);

    public List<PopularPaintingsDTO> retrievePopularPaintings();

    public List<PopularPaintingsDTO> retrievePopularGenrePaintings(Long genreId);

}
