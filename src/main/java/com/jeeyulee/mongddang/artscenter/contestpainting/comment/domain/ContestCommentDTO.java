package com.jeeyulee.mongddang.artscenter.contestpainting.comment.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContestCommentDTO {

    private Long contestPaintingId;
    private String memberId;
    private String contents;
}
