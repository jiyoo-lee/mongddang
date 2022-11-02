package com.jeeyulee.mongddang.artscenter.contestpainting.comment.service;

import com.jeeyulee.mongddang.artscenter.contestpainting.comment.domain.ContestCommentDTO;

public interface ContestCommentService {
    Boolean saveComment(ContestCommentDTO contestCommentDTO);
    Boolean deleteComment(Long contestCommentId);
}
