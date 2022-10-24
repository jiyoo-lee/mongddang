package com.jeeyulee.mongddang.painting.comment.domain;


import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaintingCommentBuilderDTO {

    private Long paintingId;
    private String memberId;
    private String contents;
    private Integer secret;
}
