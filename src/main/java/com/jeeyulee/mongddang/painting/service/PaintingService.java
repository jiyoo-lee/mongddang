package com.jeeyulee.mongddang.painting.service;

import com.jeeyulee.mongddang.painting.domain.PaintingDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateDTO;
import com.jeeyulee.mongddang.painting.domain.ConditionalPaintingsDTO;

import java.util.List;

public interface PaintingService {

    public String createPainting(PaintingDTO paintingDTO);

    public String updatePainting(Long paintingId, PaintingUpdateDTO paintingUpdateDTO);

    public Boolean deletePainting(Long paintingId);

    public List<ConditionalPaintingsDTO> retrievePopularPaintings();

    public List<ConditionalPaintingsDTO> retrievePopularGenrePaintings(Long genreId);

    public List<ConditionalPaintingsDTO> retrieveLastPaintings();

    public List<ConditionalPaintingsDTO> retrieveLastFollowingPaintings();

}
