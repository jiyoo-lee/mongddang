package com.jeeyulee.mongddang.painting.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTO {
    private String commentId;
    private String memberId;
    private String nickname;
    private String profileUrl;
    private String contents;
    private String createDatetime;
    private String description;
}
