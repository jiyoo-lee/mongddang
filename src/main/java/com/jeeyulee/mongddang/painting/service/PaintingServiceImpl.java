package com.jeeyulee.mongddang.painting.service;

import com.jeeyulee.mongddang.painting.domain.PaintingCreationDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateBuilderDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateDTO;
import com.jeeyulee.mongddang.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepository paintingRepository;
    @Override
    public Boolean createPainting(PaintingDTO paintingDTO) {
       String FileName = UUID.randomUUID().toString() + "_" +
                        paintingDTO.getDropsId() + "_" +
                        paintingDTO.getName();

        PaintingCreationDTO paintingCreationDTO = PaintingCreationDTO.builder()
                                .genreId(paintingDTO.getGenreId())
                                .dropsId(paintingDTO.getDropsId())
                                .name(paintingDTO.getName())
                                .paintingUrl(FileName + paintingDTO.getExtension())
                                .description(paintingDTO.getDescription())
                                .build();

        return paintingRepository.save(paintingCreationDTO) > 0;
    }

    @Override
    public Boolean updatePainting(Long paintingId, PaintingUpdateDTO paintingUpdateDTO) {

        String fileName = null;
        if(paintingUpdateDTO.getExtension() != null) {
            fileName = convertToUUID(paintingUpdateDTO.getGenreId(), paintingUpdateDTO.getName()); }

        PaintingUpdateBuilderDTO builderDTO = PaintingUpdateBuilderDTO.builder()
                .paintingId(paintingId)
                .genreId(paintingUpdateDTO.getGenreId())
                .name(paintingUpdateDTO.getName())
                .paintingUrl( fileName == null ? null : fileName + paintingUpdateDTO.getExtension())
                .description(paintingUpdateDTO.getDescription())
                .build();

        return paintingRepository.update(builderDTO) > 0;
    }

    @Override
    public Boolean deletePainting(Long paintingId) {

        return paintingRepository.delete(paintingId) > 0;
    }

    private String convertToUUID(Long dropsId, String file){

        String fileName = UUID.randomUUID().toString() + "_" +
                            dropsId + "_" + file;
        return fileName;
    }
}
