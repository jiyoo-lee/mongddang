package com.jeeyulee.mongddang.member.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import com.jeeyulee.mongddang.member.dto.MemberLoginDTO;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.member.service.MemberService;
import com.jeeyulee.mongddang.member.vo.MemberVO;
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

    @ApiOperation(value="한줄요약", notes="설명")
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
            result.setSuccess(true);
            result.setData(memberService.login(memberLoginDTO));
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
    public ResponseEntity<ResultDTO> updateMember(@RequestBody MemberVO memberVO){
       ResultDTO result = new ResultDTO();
       result.setSuccess(memberService.updateMember(memberVO));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
