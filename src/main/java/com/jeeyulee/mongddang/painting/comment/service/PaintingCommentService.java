package com.jeeyulee.mongddang.painting.comment.service;

import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentDTO;

public interface PaintingCommentService {

    public Boolean save(PaintingCommentDTO paintingCommentDTO);

    public Boolean update(Long commentId, PaintingCommentDTO paintingCommentDTO);

}
