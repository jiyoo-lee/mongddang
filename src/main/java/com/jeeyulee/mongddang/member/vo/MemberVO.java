package com.jeeyulee.mongddang.member.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberVO {
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String profilePicture;
    private String phoneNumber;
    private String address;
    private Boolean admin;
    private String lastAccessToken;
    private String createDatetime;
}
