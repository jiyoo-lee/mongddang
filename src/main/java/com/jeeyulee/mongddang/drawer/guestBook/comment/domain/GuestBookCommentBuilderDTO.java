package com.jeeyulee.mongddang.drawer.guestBook.comment.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GuestBookCommentBuilderDTO {

    private String userId;
    private Long guestBookId;
    private String contents;
}
