package com.jeeyulee.mongddang.artscenter.contestpainting.service;

import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingBuilderDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingDeleteDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.repository.ContestPaintingRepository;
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
public class ContestPaintingServiceImpl implements ContestPaintingService{

    private final ContestPaintingRepository contestPaintingRepository;
    private final JwtService jwtService;

    @Override
    public Boolean savePainting(ContestPaintingDTO contestPaintingDTO) {


        log.info("ArtsCenterServiceImpl savePainting ====> {}", contestPaintingDTO.toString());
        return contestPaintingRepository.savePainting(contestPaintingDTO) > 0;
    }

    @Override
    public List<ContestPaintingDTO> retrieveMyPainting(Long contestId, String userId) {

        return contestPaintingRepository.retrieveMyPainting(contestId, userId);
    }

    @Override
    public Boolean deletePainting(Long contestPaintingId) {

        try {
            String userIdOnToken = jwtService.retrieveUserId();
            ContestPaintingDeleteDTO deleteDTO = ContestPaintingDeleteDTO.builder()
                    .memberId(userIdOnToken)
                    .contestPaintingId(contestPaintingId).build();

        return contestPaintingRepository.deletePainting(deleteDTO) > 0;

        }catch (Exception e){
            throw new ResultException("삭제 중 문제가 발생하였습니다.");
        }
    }

    private String convertToUUID(Object first, String title){
        return UUID.randomUUID().toString() + "_" + first + "_" + title;
    }
}
