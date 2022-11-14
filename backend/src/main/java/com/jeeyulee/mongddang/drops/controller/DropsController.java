package com.jeeyulee.mongddang.drops.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.drops.domain.DropsDTO;
import com.jeeyulee.mongddang.drops.domain.DropsUpdateDTO;
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

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="드랍 비밀 여부 조회", notes = "드랍 비밀 여부를 조회하여 설정 시 select option으로 줄 수 있음")
    @GetMapping
    public ResponseEntity<ResultDTO> retrieveSecret(){
        ResultDTO result = new ResultDTO(true,dropsService.retrieveSecret());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @ApiOperation(value="드랍 상세조회 API", notes="드랍의 정보와 그림을 조회하는 API")
    @GetMapping("/{dropsId}")
    public ResponseEntity<ResultDTO> retrieveDropsDetail(@PathVariable Long dropsId) {
        ResultDTO result = new ResultDTO(true,dropsService.retrieveDropsDetail(dropsId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="드랍 수정 API", notes = "드랍 수정 API")
    @PutMapping("/{dropsId}")
    public ResponseEntity<ResultDTO> updateDrop(@PathVariable Long dropsId, @RequestBody DropsUpdateDTO dropsUpdateDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(dropsService.updateDrop(dropsId, dropsUpdateDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "드랍 삭제 API")
    @DeleteMapping("/{dropsId}")
    public ResponseEntity<ResultDTO> deleteDrop (@PathVariable Long dropsId){
        ResultDTO result = new ResultDTO();
        result.setSuccess(dropsService.deleteDrop(dropsId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
