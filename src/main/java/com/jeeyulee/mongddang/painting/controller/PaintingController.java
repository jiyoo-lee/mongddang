package com.jeeyulee.mongddang.painting.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.painting.domain.PaintingDTO;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateDTO;
import com.jeeyulee.mongddang.painting.exception.NotUploadPaintingException;
import com.jeeyulee.mongddang.painting.service.PaintingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/painting")
@RequiredArgsConstructor
public class PaintingController {
    private final PaintingService paintingService;

    @ApiOperation(value = "그림 업로드 API")
    @PostMapping
    public ResponseEntity<ResultDTO> createPainting(@RequestBody PaintingDTO paintingDTO){
        ResultDTO result = new ResultDTO();
        try{
            result.setData(paintingService.createPainting(paintingDTO));
            result.setSuccess(true);
        }catch(NotUploadPaintingException e){
            result.setSuccess(false);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="그림 수정 API", notes = "그림 수정 API")
    @PutMapping("/{paintingId}")
    public ResponseEntity<ResultDTO> updatePainting(@PathVariable Long paintingId,
                                                    @RequestBody PaintingUpdateDTO paintingUpdateDTO){
        ResultDTO result = new ResultDTO();
        try {
            result.setSuccess(true);
            result.setData(paintingService.updatePainting(paintingId, paintingUpdateDTO));
        } catch (UserNotFoundException | NotUploadPaintingException e) {
            result.setSuccess(false);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="그림 삭제 API", notes = "그림 삭제 API")
    @DeleteMapping
    public ResponseEntity<ResultDTO> deletePainting(Long paintingId){
        ResultDTO result = new ResultDTO();
        result.setSuccess(paintingService.deletePainting(paintingId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="인기 그림 조회 API", notes = "인기 그림 조회 API")
    @GetMapping("/popular")
    public ResponseEntity<ResultDTO> retrievePopularPaintings(){
        ResultDTO result = new ResultDTO();
        try {
            result.setData(paintingService.retrievePopularPaintings());
            result.setSuccess(true);
        }catch (NotUploadPaintingException e){
            result.setSuccess(false);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="장르 별 인기조회 API", notes = "장르별 인기 조회 API")
    @GetMapping("/popular/genre/{genreId}")
    public ResponseEntity<ResultDTO> retrievePopularGenrePaintings(@PathVariable Long genreId){
        ResultDTO result = new ResultDTO();
        try {
            result.setData(paintingService.retrievePopularGenrePaintings(genreId));
            result.setSuccess(true);
        }catch (NotUploadPaintingException e){
            result.setSuccess(false);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
