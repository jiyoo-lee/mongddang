package com.jeeyulee.mongddang.artscenter.contestpainting.mongddang.service;

import com.jeeyulee.mongddang.artscenter.contestpainting.mongddang.domain.ContestMongddangDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.mongddang.repository.ContestMongddangRepository;
import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContestMongddangServiceImpl implements ContestMongddangService{

    private final ContestMongddangRepository contestMongddangRepository;
    private final JwtService jwtService;

    @Override
    public Boolean createMongddang(Long contestPaintingId) {

        String userIdOnToken = jwtService.retrieveUserId();

        return contestMongddangRepository.save(buildDTO(contestPaintingId,userIdOnToken)) > 0;
    }

    @Override
    public Boolean deleteMongddang(Long contestPaintingId) {
        try {
            String userIdOnToken = jwtService.retrieveUserId();
        return contestMongddangRepository.delete(buildDTO(contestPaintingId,userIdOnToken)) > 0;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new ResultException("불가한 접근입니다.");
        }
    }

    private ContestMongddangDTO buildDTO (Long contestPaintingId, String userIdOnToken){

        ContestMongddangDTO mongddangDTO = ContestMongddangDTO.builder()
                .memberId(userIdOnToken)
                .contestPaintingId(contestPaintingId).build();
        return mongddangDTO;
    }
}
