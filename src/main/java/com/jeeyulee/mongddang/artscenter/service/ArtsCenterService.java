package com.jeeyulee.mongddang.artscenter.service;

import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterResponseDTO;
import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterWinnerResponseDTO;
import com.jeeyulee.mongddang.artscenter.domain.ContestDTO;

import java.util.List;

public interface ArtsCenterService {

    List<ArtsCenterResponseDTO> retrieveArtCenters();

    List<ArtsCenterWinnerResponseDTO> retrieveArtCenterWinners(Long contestId);
}
