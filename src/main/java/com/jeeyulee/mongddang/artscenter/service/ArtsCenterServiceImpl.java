package com.jeeyulee.mongddang.artscenter.service;

import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterResponseDTO;
import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterWinnerResponseDTO;
import com.jeeyulee.mongddang.artscenter.repository.ArtsCenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArtsCenterServiceImpl implements ArtsCenterService {

    private final ArtsCenterRepository artsCenterRepository;

    @Override
    public List<ArtsCenterResponseDTO> retrieveArtCenters() {
        return artsCenterRepository.findAll();
    }

    @Override
    public List<ArtsCenterWinnerResponseDTO> retrieveArtCenterWinners(Long dropsId) {
        return artsCenterRepository.findWinnerByDropsId(dropsId);
    }
}
