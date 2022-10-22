package com.jeeyulee.mongddang.drops.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.drops.domain.DropsDTO;
import com.jeeyulee.mongddang.drops.service.DropsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/drops")
@RequiredArgsConstructor
public class DropsController {

    private final DropsService dropsService;

    @ApiOperation(value="드랍 생성 API", notes="드랍 생성")
    @PostMapping
    public ResponseEntity<ResultDTO> createDrop (@RequestBody DropsDTO dropsDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(dropsService.createDrop(dropsDTO));

        return new ResponseEntity<ResultDTO>(result, HttpStatus.OK);
    }


    @ApiOperation(value="드랍 수정 API", notes = "드랍 수정 API")
    @PutMapping
    public ResponseEntity<ResultDTO> updateDrop(@RequestBody DropsDTO dropsDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(dropsService.updateDrop(dropsDTO));

        return new ResponseEntity<ResultDTO>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "드랍 삭제 API")
    @DeleteMapping("/{dropId}")
    public ResponseEntity<ResultDTO> deleteDrop (@PathVariable Long dropId){
        ResultDTO result = new ResultDTO();
        result.setSuccess(dropsService.deleteDrop(dropId));

        return new ResponseEntity<ResultDTO>(result, HttpStatus.OK);
    }
}
