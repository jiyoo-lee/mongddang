package com.jeeyulee.mongddang.artscenter.contestpainting.service;

import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingDTO;

import java.util.List;

public interface ContestPaintingService {

    public Boolean savePainting(ContestPaintingDTO contestPaintingDTO);
    public List<ContestPaintingDTO> retrieveMyPainting(Long contestId, String userId);

    public Boolean deletePainting(Long paintingId);
}
