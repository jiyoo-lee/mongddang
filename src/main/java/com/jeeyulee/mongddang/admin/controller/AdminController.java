package com.jeeyulee.mongddang.admin.controller;

import com.jeeyulee.mongddang.admin.domain.AdminResignDTO;
import com.jeeyulee.mongddang.admin.service.AdminService;
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

    @ApiOperation(value="회원 그림 조회", notes = "회원 그림 전체 조회 API")
    @GetMapping("/paintings")
    public ResponseEntity<ResultDTO> retrieveAllPaintings(){
        ResultDTO result = new ResultDTO(true, adminService.retrieveAllPaintings());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="그림 삭제 API", notes = "그림 삭제 API")
    @DeleteMapping
    public ResponseEntity<ResultDTO> deletePainting() {
        ResultDTO result = new ResultDTO(true, adminService.deleteAllPaintings());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
