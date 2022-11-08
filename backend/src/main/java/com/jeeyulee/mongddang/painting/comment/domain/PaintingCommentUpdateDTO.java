package com.jeeyulee.mongddang.painting.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaintingCommentUpdateDTO {

    private Long commentId;
    private String contents;
    private Boolean secret;
}
