package com.jeeyulee.mongddang.admin.controller;

import com.jeeyulee.mongddang.admin.domain.AdminResignDTO;
import com.jeeyulee.mongddang.admin.domain.ContestUpdateDTO;
import com.jeeyulee.mongddang.admin.service.AdminService;
import com.jeeyulee.mongddang.admin.domain.ContestDTO;
import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.painting.comment.domain.CommentDTO;
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

    @ApiOperation(value = "전체 회원 조회", notes = "회원 조회")
    @GetMapping("/members")
    public ResponseEntity<ResultDTO> retrieveAllMembers(){
        ResultDTO result = new ResultDTO(true, adminService.retrieveAllMembers());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="회원 삭제(강제탈퇴)", notes = "회원 권리자 권한으로 삭제 권한 API")
    @DeleteMapping("/members")
    public ResponseEntity<ResultDTO> resignMember(@RequestBody AdminResignDTO adminResignDTO){
        ResultDTO result = new ResultDTO(true, adminService.resignMember(adminResignDTO.getMemberId()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "예술의 몽땅 공모전 조회", notes = "예술의 몽땅 공모전 리스트 조회")
    @GetMapping("/arts-center")
    public ResponseEntity<ResultDTO> retrieveContest () {
        ResultDTO result = new ResultDTO(true,adminService.retrieveAllContest());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value="예술의 몽땅 공모전 등록(관리자페이지) API", notes="예술의 몽땅 공모전 등록")
    @PostMapping("/arts-center/contest")
    public ResponseEntity<ResultDTO> createContest(@RequestBody ContestDTO contestDTO){
        ResultDTO result = new ResultDTO(true,adminService.saveContest(contestDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="예술의 몽땅 공모전 수정 API",notes = "공모전 (제목,이미지,기간) 수정 API")
    @PutMapping("/arts-center/contest/{contestId}")
    public ResponseEntity<ResultDTO> updateContest(@PathVariable Long contestId,
                                                   @RequestBody ContestUpdateDTO commentDTO){
        ResultDTO result = new ResultDTO(true, adminService.updateContest(contestId,commentDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="전체 회원 그림 조회", notes = "회원 그림 전체 조회 API")
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

    @ApiOperation(value="관리자 페이지 회원 검색",notes = "관리자 페이지 이름, 아이디로 조회하여 회원 검색 API")
    @GetMapping("/members/{keyword}")
    public ResponseEntity<ResultDTO> findMemberByKeyword(@PathVariable String keyword){
        ResultDTO result = new ResultDTO(true, adminService.findMemberByKeyword(keyword));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "관리자 페이지 그림 검색", notes = "관리자 페이지 그림 제목으로 검색")
    @GetMapping("/paintings/{keyword}")
    public ResponseEntity<ResultDTO> findPaintingByKeyword(@PathVariable String keyword){
        ResultDTO result = new ResultDTO(true, adminService.findPaintingByKeyword(keyword));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value = "공모전 마감 API", notes = "공모전 마감 API")
    @PutMapping("/arts-center/{contestId}/deadline")
    public ResponseEntity<ResultDTO> closeContest(@PathVariable Long contestId){
        ResultDTO result = new ResultDTO(true, adminService.closeContest(contestId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value = "공모전 제목으로 검색", notes = "공모전 제목으로 검색하여 조회하는 API")
    @GetMapping("/arts-center/{keyword}")
    public ResponseEntity<ResultDTO> retrieveContestByKeyword (@PathVariable String keyword) {
        ResultDTO result = new ResultDTO(true,adminService.retrieveContestByKeyword(keyword));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
