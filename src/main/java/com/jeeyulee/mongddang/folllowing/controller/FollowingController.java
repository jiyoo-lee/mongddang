package com.jeeyulee.mongddang.folllowing.controller;

import com.jeeyulee.mongddang.common.exception.DeniedAccessException;
import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.folllowing.service.FollowingService;
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

    @ApiOperation(value="", notes = "")
    @GetMapping("/{userId}")
    public ResponseEntity<ResultDTO> followMember(@PathVariable String userId){
        ResultDTO result = new ResultDTO();
        try {
            result.setSuccess(followingService.followMember(userId));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (DeniedAccessException e){
            result.setSuccess(false);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value="", notes = "")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResultDTO> unfollowMember(@PathVariable String userId){
        ResultDTO result = new ResultDTO();
        result.setSuccess(followingService.unfollowMember(userId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
