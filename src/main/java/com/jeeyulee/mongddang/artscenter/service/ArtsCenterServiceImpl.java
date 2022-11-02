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
    public Boolean savePainting(ContestPaintingDTO contestPaintingDTO) {

        String fileName =
                convertToUUID(contestPaintingDTO.getContestId(), contestPaintingDTO.getTitle());

        ContestPaintingBuilderDTO builderDTO =
                ContestPaintingBuilderDTO.builder()
                        .contestId(contestPaintingDTO.getContestId())
                        .memberId(contestPaintingDTO.getMemberId())
                        .title(contestPaintingDTO.getTitle())
                        .contestPaintingUrl(fileName + contestPaintingDTO.getExtension())
                        .description(contestPaintingDTO.getDescription()).build();

        log.info("ArtsCenterServiceImpl savePainting ====> {}", builderDTO.toString());
        return artsCenterRepository.savePainting(builderDTO) > 0;
    }

    @Override
    public Boolean saveContest(ContestDTO contestDTO) {

        if(contestDTO.getExtension() == null){
            throw new ResultException("파일이 없습니다.");
        }

        String fileName =
                convertToUUID(contestDTO.getStartDay(),contestDTO.getTitle());
        ContestBuilderDTO contestBuilderDTO = ContestBuilderDTO.builder()
                .memberId(contestDTO.getMemberId())
                .title(contestDTO.getTitle())
                .posterUrl(fileName + contestDTO.getExtension())
                .startDay(contestDTO.getStartDay())
                .EndDay(contestDTO.getEndDay())
                .build();

        return artsCenterRepository.saveContest(contestBuilderDTO) > 0;
    }

    @Override
    public List<ArtsCenterResponseDTO> retrieveArtCenters() {
        return artsCenterRepository.findAll();
    }

    @Override
    public List<ArtsCenterWinnerResponseDTO> retrieveArtCenterWinners(Long dropsId) {
        return artsCenterRepository.findWinnerByDropsId(dropsId);
    }
    private String convertToUUID(Object first, String title){
        return UUID.randomUUID().toString() + "_" + first + "_" + title;
    }
}
