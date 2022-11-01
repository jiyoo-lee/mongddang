package com.jeeyulee.mongddang.painting.service;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.member.service.JwtService;
import com.jeeyulee.mongddang.painting.domain.*;
import com.jeeyulee.mongddang.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepository paintingRepository;

    private final JwtService jwtService;

    @Override
    public String createPainting(PaintingDTO paintingDTO){
       String fileName = convertToUUID(paintingDTO.getDropsId(), paintingDTO.getName());

        PaintingCreationDTO paintingCreationDTO = PaintingCreationDTO.builder()
                                .memberId(paintingDTO.getMemberId())
                                .genreId(paintingDTO.getGenreId())
                                .dropsId(paintingDTO.getDropsId())
                                .name(paintingDTO.getName())
                                .paintingUrl(fileName + paintingDTO.getExtension())
                                .description(paintingDTO.getDescription())
                                .build();

        int result = paintingRepository.save(paintingCreationDTO);

        if (result > 0) {
            return fileName + paintingDTO.getExtension();
        }
        throw new ResultException("그림을 업로드할 수 없습니다.");
    }

    @Override
    public String updatePainting(Long paintingId, PaintingUpdateDTO paintingUpdateDTO) {
        String fileName = convertToUUID(paintingUpdateDTO.getDropsId(), paintingUpdateDTO.getName());

        String userId = paintingRepository.findUserIdByDropsId(paintingUpdateDTO.getDropsId());
        String userIdByToken = jwtService.retrieveUserId();

        if (!userId.equals(userIdByToken)) {
            throw new ResultException("그림을 업로드할 수 없습니다.");
        }

        PaintingUpdateBuilderDTO builderDTO = PaintingUpdateBuilderDTO.builder()
                .paintingId(paintingId)
                .genreId(paintingUpdateDTO.getGenreId())
                .name(paintingUpdateDTO.getName())
                .paintingUrl(fileName + paintingUpdateDTO.getExtension())
                .description(paintingUpdateDTO.getDescription())
                .build();

        int result = paintingRepository.update(builderDTO);
        if (result > 0) {
            return fileName + paintingUpdateDTO.getExtension();
        }
        throw new ResultException("그림 업로드를 실패하였습니다. ");
    }

    @Override
    public Boolean deletePainting(Long paintingId) {

        return paintingRepository.delete(paintingId) > 0;
    }

    @Override
    public List<ConditionalPaintingsDTO> retrievePopularPaintings() {
        List<ConditionalPaintingsDTO> popularPaintingsList =
                paintingRepository.retrievePopularPaintings();
        checkEmpty(popularPaintingsList);
        return popularPaintingsList;
    }

    @Override
    public List<ConditionalPaintingsDTO> retrievePopularGenrePaintings(Long genreId) {

        List<ConditionalPaintingsDTO> popularGenrePaintings =
                paintingRepository.retrievePopularPaintingsByGenreId(genreId);
        checkEmpty(popularGenrePaintings);
        return popularGenrePaintings;
    }

    @Override
    public List<ConditionalPaintingsDTO> retrieveLastPaintings() {

        List<ConditionalPaintingsDTO> retrieveLastPaintings =
                paintingRepository.retrieveLastPaintings();
        checkEmpty(retrieveLastPaintings);
        return retrieveLastPaintings;
    }

    @Override
    public List<ConditionalPaintingsDTO> retrieveLastFollowingPaintings() {

        List<ConditionalPaintingsDTO> retrieveLastFollowingPaintings =
                paintingRepository.retrieveLastFollowingPaintings(jwtService.retrieveUserId());
        checkEmpty(retrieveLastFollowingPaintings);
        return retrieveLastFollowingPaintings;
    }

    private String convertToUUID(Long dropsId, String fileName){
        return UUID.randomUUID().toString() + "_" + dropsId + "_" + fileName;
    }

    private void checkEmpty(List<ConditionalPaintingsDTO> list) {
        if(list.isEmpty()){
            throw new ResultException("그림 업로드에 실패하였습니다.");
        }
    }
}
