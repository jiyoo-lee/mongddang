package com.jeeyulee.mongddang.member.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.member.domain.*;
import com.jeeyulee.mongddang.member.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value="회원가입", notes="회원 가입 API")
    @PostMapping("/join")
    public ResponseEntity<ResultDTO> join(@Valid @RequestBody MemberJoinDTO memberJoinDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(memberService.join(memberJoinDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="로그인", notes="설명")
    @PostMapping("/login")
    public ResponseEntity<ResultDTO> login(@Valid @RequestBody MemberLoginDTO memberLoginDTO){
        ResultDTO result = new ResultDTO(true, memberService.login(memberLoginDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="ID 중복확인", notes="중복확인")
    @ApiImplicitParam(name="userId", value="유저 아이디", example="helloThere", required=true, dataTypeClass=String.class)
    @GetMapping("/overlap")
    public ResponseEntity<ResultDTO> checkOverlap(String userId){
       ResultDTO result = new ResultDTO();
       result.setSuccess(true);
       result.setData(memberService.checkOverlap(userId));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ApiOperation(value = "회원정보 조회", notes="회원 정보 조회")
    @ApiImplicitParam(name="userId", value="유저 아이디", example ="helloThere", required=true, dataTypeClass=String.class)
    @GetMapping("/{userId}")
    public ResponseEntity<ResultDTO> findById(@PathVariable String userId){
        ResultDTO result = new ResultDTO();
            result.setData(memberService.findById(userId));
            result.setSuccess(true);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 수정", notes ="회원 정보 수정")
    @PutMapping
    public ResponseEntity<ResultDTO> updateMember(@RequestBody MemberDTO memberDTO){
       ResultDTO result = new ResultDTO();
       result.setSuccess(memberService.updateMember(memberDTO));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ApiOperation(value="회원 탈퇴", notes = "회원 탈퇴 API")
    @DeleteMapping
    public ResponseEntity<ResultDTO> resignMember(MemberDeleteDTO memberDeleteDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(memberService.resign(memberDeleteDTO));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ApiOperation(value="아이디 찾기 인증번호 생성", notes = "아이디 찾는 경우, 이메일로 아이디 유효성 검증하여 인증번호 메일발송")
    @PostMapping("/seeking-id/auth-number")
    public ResponseEntity<ResultDTO> createAuthNumber(@RequestBody EmailDTO emailDto){
        ResultDTO result = new ResultDTO();
        result.setData(memberService.retrieveAuthNumber(emailDto.getEmail()));
        result.setSuccess(true);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="아이디 조회", notes = "회원 아이디 조회")
    @GetMapping("/user-id")
    public ResponseEntity<ResultDTO> findId(String email){
        ResultDTO result = new ResultDTO();
        result.setSuccess(true);
        result.setData(memberService.findIdByEmail(email));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="비밀번호 변경 인증번호 생성", notes="아이디/이메일 유효성 검증 후, 인증번호 메일 발송하여 응답")
    @GetMapping("/update-password/auth-number")
    public ResponseEntity<ResultDTO> findPassword(FindPasswordDTO findPasswordDTO){
        ResultDTO result = new ResultDTO();
            result.setData(memberService.findByIdAndEmail(findPasswordDTO));
            result.setSuccess(true);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ApiOperation(value="비밀번호 변경", notes="비밀번호 찾기 이후 변경처리하는 API")
    @PutMapping("/password")
    public ResponseEntity<ResultDTO> updatePassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        ResultDTO result = new ResultDTO();
        result.setSuccess(memberService.updatePassword(passwordUpdateDTO));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="프로필사진 업로드", notes="프로필 사진을 업로드하는 API")
    @PutMapping("/{userId}/profile-picture")
    public ResponseEntity<ResultDTO> uploadProfilePicture(@PathVariable String userId,
                                                          @Valid @RequestBody ProfilePictureUploadDTO profilePictureUploadDTO) {
        ResultDTO result = new ResultDTO();
        result.setSuccess(memberService.uploadProfilePicture(userId, profilePictureUploadDTO.getProfileUrl()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
