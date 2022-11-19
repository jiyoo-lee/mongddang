package com.jeeyulee.mongddang.artscenter.contestpainting.comment.controller;

import com.jeeyulee.mongddang.artscenter.contestpainting.comment.domain.ContestCommentDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.comment.service.ContestCommentService;
import com.jeeyulee.mongddang.common.result.ResultDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/arts-center/painting")
public class ContestCommentController {

    private final ContestCommentService commentService;


    @ApiOperation(value="공모전 댓글 등록 API", notes = "공모전 댓글 등록 API")
    @PostMapping("/comment")
    public ResponseEntity<ResultDTO> createComment(@RequestBody ContestCommentDTO contestCommentDTO){
        ResultDTO result = new ResultDTO(true,commentService.saveComment(contestCommentDTO) );

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "공모전 댓글 조회 API", notes = "공모전 댓글 조회 API")
    @GetMapping("/{paintingId}")
    public ResponseEntity<ResultDTO> retrieveContestComments(@PathVariable Long paintingId){
        ResultDTO result = new ResultDTO(true, commentService.retrieveContestComments(paintingId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value="공모전 댓글 삭제 API", notes = "공모전 댓글 삭제 API")
    @DeleteMapping("/comment/{contestCommentId}")
    public ResponseEntity<ResultDTO> deleteContestComment(@PathVariable Long contestCommentId){
        ResultDTO result = new ResultDTO(true, commentService.deleteComment(contestCommentId));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
