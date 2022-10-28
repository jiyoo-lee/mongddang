package com.jeeyulee.mongddang.artscenter.controller;

import com.jeeyulee.mongddang.artscenter.service.ArtsCenterService;
import com.jeeyulee.mongddang.common.result.ResultDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value="", notes = "")
    @GetMapping("/winners")
    public ResponseEntity<ResultDTO> retrieveWinnersOfHistory(){

        ResultDTO result = new ResultDTO();
        result.setData(artsCenterService.retrieveWinnersOfHistory());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
