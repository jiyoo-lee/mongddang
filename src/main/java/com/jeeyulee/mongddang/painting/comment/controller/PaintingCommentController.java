package com.jeeyulee.mongddang.painting.comment.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentDTO;
import com.jeeyulee.mongddang.painting.comment.service.PaintingCommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/painting/comment")
public class PaintingCommentController {

    private final PaintingCommentService paintingCommentService;

    @ApiOperation(value = "그림 댓글 등록 API", notes = "그림 댓글 등록 API")
    @PostMapping
    public ResponseEntity<ResultDTO> createPaintingComment(@RequestBody PaintingCommentDTO paintingCommentDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(paintingCommentService.save(paintingCommentDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 수정 기능", notes="댓글 수정 API")
    @PutMapping("/{commentId}")
    public ResponseEntity<ResultDTO> updatePaintingComment(@PathVariable Long commentId,
                                                           @RequestBody PaintingCommentDTO paintingCommentDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(paintingCommentService.update(commentId, paintingCommentDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value= "댓글 삭제 API", notes = "댓글 삭제 API")
    @DeleteMapping({"/{commentId}"})
    public ResponseEntity<ResultDTO> deletePaintingComment(@PathVariable Long commentId){
        ResultDTO result = new ResultDTO();
        result.setSuccess(paintingCommentService.delete(commentId));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
