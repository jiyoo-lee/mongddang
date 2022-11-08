package com.jeeyulee.mongddang.artscenter.service;

import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterPaintingDetailDTO;
import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterResponseDTO;
import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterWinnerResponseDTO;

import java.util.List;

public interface ArtsCenterService {

    List<ArtsCenterResponseDTO> retrieveArtCenters();

    List<ArtsCenterWinnerResponseDTO> retrieveArtCenterWinners(Long contestId);

    List<ArtsCenterPaintingDetailDTO> retrievePaintingDetail(Long contestId, Long paintingId);
}
