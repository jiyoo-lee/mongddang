package com.jeeyulee.mongddang.painting.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingDTO;
import com.jeeyulee.mongddang.painting.service.PaintingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/painting")
@RequiredArgsConstructor
public class PaintingController {

    private final PaintingService paintingService;

    @ApiOperation(value = "그림 업로드 API")
    @PostMapping
    public ResponseEntity<ResultDTO> createPainting(PaintingDTO paintingDTO){
        ResultDTO result = new ResultDTO();
        try{
            result.setData(paintingService.createPainting(paintingDTO));
            result.setSuccess(true);
        }catch(Exception e){
            result.setSuccess(false);
        }
        return new ResponseEntity<ResultDTO>(result, HttpStatus.OK);
    }

}
