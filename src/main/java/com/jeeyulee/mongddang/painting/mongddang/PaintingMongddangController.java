package com.jeeyulee.mongddang.painting.mongddang;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/painting/mongddang")
public class PaintingMongddangController {

    private final PaintingMongddangService paintingMongddangService;

    @ApiOperation(value="그림 좋아요 기능 API", notes = "그림 좋아요 기능 API")
    @GetMapping
    public ResponseEntity<ResultDTO> createMongddang(Long paintingId){

        ResultDTO result = new ResultDTO();
        result.setSuccess(paintingMongddangService.saveMongddang(paintingId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "그림 좋아요 취소 API", notes = "그림 좋아요 취소 API")
    @DeleteMapping
    public ResponseEntity<ResultDTO> cancelMongddang(Long paintingId){

        ResultDTO result = new ResultDTO();
        result.setSuccess(paintingMongddangService.cancelMongddang(paintingId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
