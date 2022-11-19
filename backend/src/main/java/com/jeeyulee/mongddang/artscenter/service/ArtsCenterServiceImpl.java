package com.jeeyulee.mongddang.artscenter.service;

import com.jeeyulee.mongddang.artscenter.domain.*;
import com.jeeyulee.mongddang.artscenter.repository.ArtsCenterRepository;
import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.member.service.JwtService;
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
    private final JwtService jwtService;
    @Override
    public List<ArtsCenterResponseDTO> retrieveInProgressArtCenters() {
        return artsCenterRepository.findInProgress();
    }

    @Override
    public List<ArtsCenterWinnerResponseDTO> retrieveArtCenterWinners(Long contestId) {
        String userIdOnToken = jwtService.retrieveUserId();
        return artsCenterRepository.findWinnerByDropsId(contestId, userIdOnToken);
    }

    @Override
    public List<ArtsCenterPaintingDetailDTO> retrievePaintingDetail(Long contestId, Long paintingId) {
        String userIdOnToken = jwtService.retrieveUserId();
        if(contestId == null || paintingId == null) {
            throw new ResultException("잘못된 접근입니다.");
        }
        return artsCenterRepository.findPaintingDetail(contestId, paintingId, userIdOnToken);
    }
}
