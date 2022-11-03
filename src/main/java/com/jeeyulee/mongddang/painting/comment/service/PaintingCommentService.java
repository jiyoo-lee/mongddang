package com.jeeyulee.mongddang.painting.comment.service;

import com.jeeyulee.mongddang.painting.comment.domain.CommentDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentDTO;

import java.util.List;

public interface PaintingCommentService {

    Boolean save(PaintingCommentDTO paintingCommentDTO);

    List<CommentDTO> retrieveComments(Long paintingId);

    Boolean update(Long commentId, PaintingCommentDTO paintingCommentDTO);

    Boolean delete(Long commentId);



}
