package com.jeeyulee.mongddang.painting.service;

import com.jeeyulee.mongddang.painting.domain.PaintingCreationDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingDTO;
import com.jeeyulee.mongddang.painting.repository.PaintingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
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
}
