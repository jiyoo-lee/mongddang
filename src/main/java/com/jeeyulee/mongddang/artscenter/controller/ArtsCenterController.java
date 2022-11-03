package com.jeeyulee.mongddang.artscenter.controller;

import com.jeeyulee.mongddang.artscenter.service.ArtsCenterService;
import com.jeeyulee.mongddang.common.result.ResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/arts-center")
public class ArtsCenterController {

    private final ArtsCenterService artsCenterService;



    @ApiOperation(value="현재 진행 중인 예술의몽땅 조회 API", notes="진행 중인 공모전 최신순 목록으로 응답")
    @GetMapping
    public ResponseEntity<ResultDTO> retrieveArtCenters(){
        ResultDTO result = new ResultDTO(true, artsCenterService.retrieveArtCenters());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="예술의몽땅별 수상작목록 조회 API", notes="예술의몽땅별 1~10위의 수상작목록을 조회하여 응답")
    @GetMapping("/{contestId}")
    public ResponseEntity<ResultDTO> retrieveArtCenterWinners(@PathVariable Long contestId) {
        ResultDTO result = new ResultDTO(true, artsCenterService.retrieveArtCenterWinners(contestId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
