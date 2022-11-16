package com.jeeyulee.mongddang.folllowing.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.folllowing.service.FollowingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/following")
@RequiredArgsConstructor
public class FollowingController {
    private final FollowingService followingService;

    @ApiOperation(value="멤버 팔로잉", notes = "멤버 팔로잉 API")
    @PostMapping("/{userId}")
    public ResponseEntity<ResultDTO> followMember(@PathVariable String userId){
        ResultDTO result = new ResultDTO();
            result.setSuccess(followingService.followMember(userId));
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "팔로잉 목록 조회", notes = "팔로잉 목록 조회 API")
    @GetMapping("/my-followings")
    public ResponseEntity<ResultDTO> retrieveFollowing(){
        ResultDTO result = new ResultDTO();
            result.setSuccess(true);
            result.setData(followingService.retrieveMyFollowings());
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "친구 추천 API", notes = "친구 추천 API")
    @GetMapping("/recommend")
    public ResponseEntity<ResultDTO> retrieveRecommendFriends(){
        ResultDTO result = new ResultDTO();
            result.setData(followingService.retrieveRecommendFriends());
            result.setSuccess(true);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="멤버 언팔로잉 API", notes = "멤버 언팔로잉 API")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResultDTO> unfollowMember(@PathVariable String userId){
        ResultDTO result = new ResultDTO();
        result.setSuccess(followingService.unfollowMember(userId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "팔로워 명수 조회", notes = "메인 피드에 팔로워 수를 보여 준다.")
    @GetMapping("/count/{userId}")
    public ResponseEntity<ResultDTO> followers(@PathVariable String userId){
        ResultDTO result = new ResultDTO(true, followingService.retrieveCountFollowers(userId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "일주일 이내 업로드 한 친구 조회 API")
    @GetMapping("/last-updated/{userId}")
    public ResponseEntity<ResultDTO> retrieveLastUpdatedFriends(@PathVariable String userId){
        ResultDTO result = new ResultDTO(true, followingService.retrieveLastUpdatedFriends(userId));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
