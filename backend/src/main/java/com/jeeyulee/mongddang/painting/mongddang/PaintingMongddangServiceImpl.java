package com.jeeyulee.mongddang.painting.mongddang;

import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaintingMongddangServiceImpl implements PaintingMongddangService {

    private final PaintingMongddangRepository paintingMongddangRepository;
    private final JwtService jwtService;

    @Override
    public Boolean saveMongddang(Long paintingId) {

        String userIdOnToken = jwtService.retrieveUserId();

        return paintingMongddangRepository.save(paintingId, userIdOnToken) > 0;
    }

    @Override
    public Boolean cancelMongddang(Long paintingId) {

        String userIdOnToken = jwtService.retrieveUserId();

        return paintingMongddangRepository.delete(paintingId, userIdOnToken) > 0;
    }
}
