package com.jeeyulee.mongddang.drawer.guestBook.comment.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentDTO;
import com.jeeyulee.mongddang.drawer.guestBook.comment.service.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drawer")
public class GuestBookCommentController {

    private final CommentService commentService;

    @ApiOperation(value="방명록 댓글 등록 API", notes = "방명록 댓글 등록 API")
    @PostMapping("/{userId}/guestbook/comment")
    public ResponseEntity<ResultDTO> createGuestBookComment(@PathVariable String userId,
                                                            GuestBookCommentDTO guestBookCommentDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(commentService.createGuestBookComment(userId, guestBookCommentDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
