package com.jeeyulee.mongddang.artscenter.contestpainting.controller;

import com.jeeyulee.mongddang.artscenter.contestpainting.domain.ContestPaintingDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.service.ContestPaintingService;
import com.jeeyulee.mongddang.common.result.ResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/arts-center/painting")
public class ContestPaintingController {

    private final ContestPaintingService contestPaintingService;

    @ApiOperation(value="예술의 몽땅 그림 등록 API", notes = "진행 중인 예술의 몽땅전에만 등록 가능")
    @PostMapping
    public ResponseEntity<ResultDTO> createContestPainting(@RequestBody ContestPaintingDTO contestPaintingDTO){
        ResultDTO result = new ResultDTO(true, contestPaintingService.savePainting(contestPaintingDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="예술의 몽땅 공모전 그림 삭제 API", notes = "예술의 몽땅 공모전 그림 삭제 API")
    @DeleteMapping("/{contestPaintingId}")
    public ResponseEntity<ResultDTO> deleteContestPainting(@PathVariable Long contestPaintingId){

        ResultDTO result = new ResultDTO(true, contestPaintingService.deletePainting(contestPaintingId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
