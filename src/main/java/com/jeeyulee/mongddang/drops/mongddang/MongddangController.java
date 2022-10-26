package com.jeeyulee.mongddang.drops.mongddang;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drops/mongddang")
public class MongddangController {

    private final MongddangService mongddangService;


    @ApiOperation(value="드랍 좋아요 생성 API", notes = "드랍 좋아요 기능")
    @PostMapping ("/{dropsId}")
    public ResponseEntity<ResultDTO> createMongddang(@PathVariable Long dropsId){

        ResultDTO result = new ResultDTO();
        result.setSuccess(mongddangService.saveMongddang(dropsId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="드랍 좋아요 취소 API", notes = "드랍 좋아요 삭제 기능")
    @DeleteMapping("/{dropsId}")
    public ResponseEntity<ResultDTO> cancelMongddang(@PathVariable Long dropsId){

        ResultDTO result = new ResultDTO();
        result.setSuccess(mongddangService.cancelMongddang(dropsId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
