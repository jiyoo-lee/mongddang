package com.jeeyulee.mongddang.artscenter.contestpainting.service;

import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingDTO;

public interface ContestPaintingService {

    public Boolean savePainting(ContestPaintingDTO contestPaintingDTO);

    public Boolean deletePainting(Long paintingId);
}
