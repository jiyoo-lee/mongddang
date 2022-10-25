package com.jeeyulee.mongddang.folllowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FollowingDTO {
    private String memberId;
    private String profileUrl;
    private String updated;
}
