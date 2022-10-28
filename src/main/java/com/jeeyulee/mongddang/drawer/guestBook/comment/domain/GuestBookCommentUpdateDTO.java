package com.jeeyulee.mongddang.drawer.guestBook.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuestBookCommentUpdateDTO {
    private String contents;
    private Long commentId;
}
