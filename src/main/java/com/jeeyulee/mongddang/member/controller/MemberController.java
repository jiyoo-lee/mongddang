package com.jeeyulee.mongddang.member.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.member.domain.FindPasswordDTO;
import com.jeeyulee.mongddang.member.domain.MemberJoinDTO;
import com.jeeyulee.mongddang.member.domain.MemberLoginDTO;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.member.service.MemberService;
import com.jeeyulee.mongddang.member.domain.MemberDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/member")
@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation(value="회원가입", notes="회원 가입 API")
    @PostMapping("/join")
    public ResponseEntity<ResultDTO> join(@RequestBody MemberJoinDTO memberJoinDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(memberService.join(memberJoinDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="로그인", notes="설명")
    @PostMapping("/login")
    public ResponseEntity<ResultDTO> login(@RequestBody MemberLoginDTO memberLoginDTO){
        ResultDTO result = new ResultDTO();
        try {
            result.setData(memberService.login(memberLoginDTO));
            result.setSuccess(true);
        } catch (UserNotFoundException e) {
            result.setSuccess(false);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="ID중복확인", notes="중복확인")
    @ApiImplicitParam(name="userId", value="유저 아이디", example="helloThere", required=true, dataTypeClass=String.class)
    @GetMapping("/overlap")
    public ResponseEntity<ResultDTO> checkOverlap(String userId){
       ResultDTO result = new ResultDTO();
       result.setSuccess(memberService.checkOverlap(userId));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ApiOperation(value = "회원정보 조회", notes="회원 정보 조회")
    @ApiImplicitParam(name="userId", value="유저 아이디", example ="helloThere", required=true, dataTypeClass=String.class)
    @GetMapping("/{userId}")
    public ResponseEntity<ResultDTO> findById(@PathVariable String userId){
        ResultDTO result = new ResultDTO();
        try{
            result.setData(memberService.findById(userId));
            result.setSuccess(true);
        }catch (UserNotFoundException e){
            result.setSuccess(false);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정")
    @PutMapping("/")
    public ResponseEntity<ResultDTO> updateMember(@RequestBody MemberDTO memberDTO){
       ResultDTO result = new ResultDTO();
       result.setSuccess(memberService.updateMember(memberDTO));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ApiOperation(value="회원 탈퇴", notes = "회원 탈퇴 API")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResultDTO> resignMember(@PathVariable String userId){
        ResultDTO result = new ResultDTO();
        result.setSuccess(memberService.resign(userId));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ApiOperation(value="비밀번호 찾기", notes="회원 비밀번호 찾기")
    @GetMapping("/user-info/match")
    public ResponseEntity<ResultDTO> findByIdAndPhoneNumber(FindPasswordDTO findPasswordDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(memberService.findByIdAndPhoneNumber(findPasswordDTO));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ApiOperation(value="아이디 찾기", notes = "회원 아이디 찾기")
    @GetMapping("/user-id")
    public ResponseEntity<ResultDTO> findIdByPhoneNumber(String phoneNumber){
        ResultDTO result = new ResultDTO();
        try {
            result.setSuccess(true);
            result.setData(memberService.findIdByPhoneNumber(phoneNumber));
        }catch (UserNotFoundException e){
            result.setSuccess(false);
            result.setData(e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/mail-test")
    public ResponseEntity<ResultDTO> testSendMail() {
        ResultDTO result = new ResultDTO();
        memberService.mailTest();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
