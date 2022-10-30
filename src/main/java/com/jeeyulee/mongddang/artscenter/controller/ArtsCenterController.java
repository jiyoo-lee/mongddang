package com.jeeyulee.mongddang.artscenter.controller;

import com.jeeyulee.mongddang.artscenter.service.ArtsCenterService;
import com.jeeyulee.mongddang.common.result.ResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/arts-center")
public class ArtsCenterController {

    private final ArtsCenterService artsCenterService;

    @ApiOperation(value="모든 예술의몽땅 조회 API", notes = "모든 예술의몽땅을 최신순 목록으로 응답")
    @GetMapping
    public ResponseEntity<ResultDTO> retrieveArtCenters(){
        ResultDTO result = new ResultDTO(true, artsCenterService.retrieveArtCenters());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
