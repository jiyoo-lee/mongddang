package com.jeeyulee.mongddang.drawer.guestBook.comment.service;

import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentDTO;
import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentUpdateDTO;

public interface GuestBookCommentService {

    public Boolean createGuestBookComment(String userId, GuestBookCommentDTO guestBookCommentDTO);

    public Boolean updateGuestBookComment(String userId, GuestBookCommentUpdateDTO guestBookCommentUpdateDTO);

    public Boolean deleteGuestBookComment(String userId, Long commentId);
}
