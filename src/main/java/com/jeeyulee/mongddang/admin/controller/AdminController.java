package com.jeeyulee.mongddang.admin.controller;

import com.jeeyulee.mongddang.admin.domain.AdminResignDTO;
import com.jeeyulee.mongddang.admin.service.AdminService;
import com.jeeyulee.mongddang.artscenter.domain.ContestDTO;
import com.jeeyulee.mongddang.common.result.ResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @ApiOperation(value = "회원 조회", notes = "회원 조회")
    @GetMapping("/members")
    public ResponseEntity<ResultDTO> retrieveAllMembers(){
        ResultDTO result = new ResultDTO(true, adminService.retrieveAllMembers());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="회원 삭제", notes = "회원 권리자 권한으로 삭제 권한 API")
    @DeleteMapping("/members")
    public ResponseEntity<ResultDTO> resignMember(AdminResignDTO adminResignDTO){
        ResultDTO result = new ResultDTO(true, adminService.resignMember(adminResignDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value="예술의 몽땅 공모전 등록(관리자페이지) API", notes="예술의 몽땅 공모전 등록")
    @PostMapping("/arts-center/contest")
    public ResponseEntity<ResultDTO> createContest(@RequestBody ContestDTO contestDTO){
        ResultDTO result = new ResultDTO(true,adminService.saveContest(contestDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="회원 그림 조회", notes = "회원 그림 전체 조회 API")
    @GetMapping("/paintings")
    public ResponseEntity<ResultDTO> retrieveAllPaintings(){
        ResultDTO result = new ResultDTO(true, adminService.retrieveAllPaintings());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="그림 삭제 API", notes = "그림 삭제 API")
    @DeleteMapping("/painting/{paintingId}")
    public ResponseEntity<ResultDTO> deletePainting(@PathVariable Long paintingId) {
        ResultDTO result = new ResultDTO(true, adminService.deleteAllPaintings(paintingId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
