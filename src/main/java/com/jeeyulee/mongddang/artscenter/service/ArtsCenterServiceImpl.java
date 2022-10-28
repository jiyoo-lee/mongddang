package com.jeeyulee.mongddang.artscenter.service;

import com.jeeyulee.mongddang.artscenter.domain.ArtsCenterDTO;
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
    public List<ArtsCenterDTO> retrieveWinnersOfHistory() {

        return artsCenterRepository.retrieveWinners();
    }
}
