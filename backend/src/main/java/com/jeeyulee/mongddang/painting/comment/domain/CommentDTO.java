package com.jeeyulee.mongddang.painting.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {
    private String memberId;
    private String nickname;
    private String contents;
    private String createDatetime;
}
