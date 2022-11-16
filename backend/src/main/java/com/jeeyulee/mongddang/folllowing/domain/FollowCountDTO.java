package com.jeeyulee.mongddang.folllowing.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FollowCountDTO {
    private String followers;
    private String followings;
}
