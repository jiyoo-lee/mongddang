package com.jeeyulee.mongddang.drawer.guestBook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuestBookAndCommentDTO {
    private Long guestBookId;
    private String guestId;
    private String guestBookContents;
    private String guestBookCreateDatetime;
    private Long commentId;
    private String commentContents;
    private String commentCreateDatetime;
}
