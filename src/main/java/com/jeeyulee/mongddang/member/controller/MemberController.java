package com.jeeyulee.mongddang.member.controller;

import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import com.jeeyulee.mongddang.member.service.MemberService;
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
    public ResponseEntity<Boolean> join(@RequestBody MemberJoinDTO memberJoinDTO){
        return new ResponseEntity<>(memberService.join(memberJoinDTO), HttpStatus.OK);
    }
}
