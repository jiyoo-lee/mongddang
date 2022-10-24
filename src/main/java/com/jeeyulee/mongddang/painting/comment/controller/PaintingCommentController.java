package com.jeeyulee.mongddang.painting.comment.controller;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentDTO;
import com.jeeyulee.mongddang.painting.comment.service.PaintingCommentService;
import com.jeeyulee.mongddang.painting.service.PaintingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

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

    @ApiOperation(value = "", notes="")
    @PutMapping("/{commentId}")
    public ResponseEntity<ResultDTO> updatePaintingComment(@PathVariable Long commentId,
                                                           @RequestBody PaintingCommentDTO paintingCommentDTO ){
        ResultDTO result = new ResultDTO();
        result.setSuccess(paintingCommentService.update(commentId, paintingCommentDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
