package com.jeeyulee.mongddang.artscenter.service;

import com.jeeyulee.mongddang.artscenter.domain.*;
import com.jeeyulee.mongddang.artscenter.repository.ArtsCenterRepository;
import com.jeeyulee.mongddang.common.result.ResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public List<ArtsCenterWinnerResponseDTO> retrieveArtCenterWinners(Long contestId) {
        return artsCenterRepository.findWinnerByDropsId(contestId);
    }
}
