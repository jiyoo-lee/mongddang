package com.jeeyulee.mongddang.painting.service;

import com.jeeyulee.mongddang.painting.domain.*;

import java.util.List;

public interface PaintingService {

    public Boolean createPainting(PaintingDTO paintingDTO);

    public String updatePainting(Long paintingId, PaintingUpdateDTO paintingUpdateDTO);

    public Boolean deletePainting(Long paintingId);

    public List<ConditionalPaintingsDTO> retrievePopularPaintings();

    public List<ConditionalPaintingsDTO> retrievePopularGenrePaintings(Long genreId);

    public List<ConditionalPaintingsDTO> retrieveLastPaintings();

    public List<FeedPaintingsDTO> retrieveLastFollowingPaintings(Integer page, Integer size);

    public List<PaintingMongddangDTO> retrieveMongddangPaintings(String userId);

}
