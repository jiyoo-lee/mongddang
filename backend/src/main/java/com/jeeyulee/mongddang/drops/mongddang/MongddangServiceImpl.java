package com.jeeyulee.mongddang.drops.mongddang;

import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MongddangServiceImpl implements MongddangService{

    private final MongddangRepository mongddangRepository;
    private final JwtService jwtService;

    @Override
    public Boolean saveMongddang(Long dropsId) {

        String userIdOnToken = jwtService.retrieveUserId();

        return mongddangRepository.save(dropsId, userIdOnToken) > 0;
    }

    @Override
    public Boolean cancelMongddang(Long dropsId) {

        String userIdOnToken = jwtService.retrieveUserId();

        return mongddangRepository.delete(dropsId, userIdOnToken) > 0 ;
    }
}
