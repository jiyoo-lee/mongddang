package com.jeeyulee.mongddang.painting.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaintingCommentDTO {
    private Long paintingId;
    private String contents;
    private Boolean secret;
}
