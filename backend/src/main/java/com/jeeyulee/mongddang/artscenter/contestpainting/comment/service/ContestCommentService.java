package com.jeeyulee.mongddang.artscenter.contestpainting.comment.service;

import com.jeeyulee.mongddang.artscenter.contestpainting.comment.domain.ContestCommentDTO;
import com.jeeyulee.mongddang.painting.comment.domain.CommentDTO;

import java.util.List;

public interface ContestCommentService {
    Boolean saveComment(ContestCommentDTO contestCommentDTO);
    Boolean deleteComment(Long contestCommentId);
    List<CommentDTO> retrieveContestComments(Long paintingId);
}
