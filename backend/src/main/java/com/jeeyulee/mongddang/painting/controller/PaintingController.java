package com.jeeyulee.mongddang.painting.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.common.result.ResultException;
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
        result.setSuccess(paintingService.createPainting(paintingDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="그림 수정 API", notes = "그림 수정 API")
    @PutMapping("/{paintingId}")
    public ResponseEntity<ResultDTO> updatePainting(@PathVariable Long paintingId,
                                                    @RequestBody PaintingUpdateDTO paintingUpdateDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(true);
        result.setData(paintingService.updatePainting(paintingId, paintingUpdateDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="그림 삭제 API", notes = "그림 삭제 API")
    @DeleteMapping("/{paintingId}")
    public ResponseEntity<ResultDTO> deletePainting(@PathVariable Long paintingId){
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
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (NotUploadPaintingException e){
            result.setSuccess(false);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value="장르 별 인기 그림 조회 API", notes = "장르별 인기 그림 조회 API")
    @GetMapping("/popular/genre/{genreId}")
    public ResponseEntity<ResultDTO> retrievePopularGenrePaintings(@PathVariable Long genreId){
        ResultDTO result = new ResultDTO();
        try {
            result.setData(paintingService.retrievePopularGenrePaintings(genreId));
            result.setSuccess(true);

            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (NotUploadPaintingException e){
            result.setSuccess(false);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value="모든 최신 그림 조회 API",notes = "최신보기 API")
    @GetMapping("")
    public ResponseEntity<ResultDTO> retrieveLastPainting(){
        ResultDTO result = new ResultDTO();
        result.setSuccess(true);
        result.setData(paintingService.retrieveLastPaintings());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="팔로잉한 친구들의 최신 그림보기 API", notes = "팔로잉한 친구들의 최신 그림 보기API")
    @GetMapping("/following")
    public ResponseEntity<ResultDTO> retrieveLastFollowingPaintings(){

        ResultDTO result = new ResultDTO();
            result.setSuccess(true);
            result.setData(paintingService.retrieveLastFollowingPaintings());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
