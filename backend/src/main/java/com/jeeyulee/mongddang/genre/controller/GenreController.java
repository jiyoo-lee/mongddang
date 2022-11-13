package com.jeeyulee.mongddang.genre.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.genre.service.GenreService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/genre")
@RequiredArgsConstructor
@RestController
public class GenreController {

    private final GenreService genreService;

    @ApiOperation(value="모든 장르 조회 API", notes="몽땅에서 관리되는 모든 장르의 id, name을 조회하는 API")
    @GetMapping
    public ResponseEntity<ResultDTO> retrieveGenre() {
        ResultDTO result = new ResultDTO(true, genreService.retrieveGenre());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
