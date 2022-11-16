package com.jeeyulee.mongddang.drawer.guestBook.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GuestBookResponseDTO {
    private Long guestBookId;
    private String guestId;
    private String contents;
    private String guestBookCreateDatetime;
    private GuestBookResponseCommentDTO comment;
}
