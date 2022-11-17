package com.jeeyulee.mongddang.drawer.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DrawerUserInfoDTO {
    private String profileUrl;
    private Integer followerCount;
    private Integer followingCount;
    private Boolean isFollow;
}
