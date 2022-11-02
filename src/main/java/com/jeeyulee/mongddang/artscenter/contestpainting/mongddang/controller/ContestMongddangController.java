package com.jeeyulee.mongddang.artscenter.contestpainting.mongddang.controller;

import com.jeeyulee.mongddang.artscenter.contestpainting.mongddang.service.ContestMongddangService;
import com.jeeyulee.mongddang.common.result.ResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/painting")
public class ContestMongddangController {

    private final ContestMongddangService contestMongddangService;

    @ApiOperation(value="예술의 몽땅 공모전 좋아요 생성 API",notes = "예술의 몽땅 공모전 좋아요 생성 API")
    @PostMapping("/{contestPaintingId}/mongddang")
    public ResponseEntity<ResultDTO> createContestMongddang(@PathVariable Long contestPaintingId){
        ResultDTO result = new ResultDTO(true,contestMongddangService.createMongddang(contestPaintingId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="예술의 몽땅 좋아요 취소 기능 API", notes = "예술의 몽땅 좋아요 취소 기능 API")
    @DeleteMapping("/{contestPaintingId}/mongddang")
    public ResponseEntity<ResultDTO> deleteContestMongddang(@PathVariable Long contestPaintingId){
        ResultDTO result = new ResultDTO(true, contestMongddangService.deleteMongddang(contestPaintingId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
