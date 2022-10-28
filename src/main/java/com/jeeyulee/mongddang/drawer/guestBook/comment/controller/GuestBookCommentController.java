package com.jeeyulee.mongddang.drawer.guestBook.comment.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentDTO;
import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentUpdateDTO;
import com.jeeyulee.mongddang.drawer.guestBook.comment.service.GuestBookCommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drawer")
public class GuestBookCommentController {

    private final GuestBookCommentService guestBookCommentService;

    @ApiOperation(value="방명록 댓글 등록 API", notes = "방명록 댓글 등록 API")
    @PostMapping("/{userId}/guestbook/comment")
    public ResponseEntity<ResultDTO> createGuestBookComment(@PathVariable String userId,
                                                            GuestBookCommentDTO guestBookCommentDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(guestBookCommentService.createGuestBookComment(userId, guestBookCommentDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value="방명록 댓글 수정 API", notes = "방명록 댓글 수정 API")
    @PutMapping("/{userId}/guestbook/comment")
    public ResponseEntity<ResultDTO> updateGuestBookComment(@PathVariable String userId,
                                                            GuestBookCommentUpdateDTO guestBookCommentUpdateDTO){

        ResultDTO result = new ResultDTO();
        result.setSuccess(guestBookCommentService.updateGuestBookComment(userId, guestBookCommentUpdateDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "방명록 댓글 삭제 API", notes = "방명록 댓글 삭제 API")
    @DeleteMapping("{userId}/guestbook/comment/{commentId}")
    public ResponseEntity<ResultDTO> deleteGuestBookComment(@PathVariable String userId,
                                                            @PathVariable Long commentId){

        ResultDTO result = new ResultDTO();
        result.setSuccess(guestBookCommentService.deleteGuestBookComment(userId,commentId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
