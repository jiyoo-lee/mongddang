package com.jeeyulee.mongddang.artscenter.service;

import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterResponseDTO;

import java.util.List;

public interface ArtsCenterService {

    public List<ArtsCenterResponseDTO> retrieveArtCenters();
}
