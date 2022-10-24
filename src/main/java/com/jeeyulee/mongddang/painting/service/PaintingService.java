package com.jeeyulee.mongddang.painting.service;

import com.jeeyulee.mongddang.painting.domain.PaintingDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateDTO;

public interface PaintingService {

    public Boolean createPainting(PaintingDTO paintingDTO);

    public Boolean updatePainting(Long paintingId, PaintingUpdateDTO paintingUpdateDTO);

    public Boolean deletePainting(Long paintingId);

}
