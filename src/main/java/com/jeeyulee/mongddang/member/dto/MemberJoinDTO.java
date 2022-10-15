package com.jeeyulee.mongddang.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDTO {
    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String address;
}
