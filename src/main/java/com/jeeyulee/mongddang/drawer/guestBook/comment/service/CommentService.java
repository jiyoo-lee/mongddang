package com.jeeyulee.mongddang.drawer.guestBook.comment.service;

import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentDTO;

public interface CommentService {

    public Boolean createGuestBookComment(String userId, GuestBookCommentDTO guestBookCommentDTO);
}
