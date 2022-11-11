package com.jeeyulee.mongddang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberJoinBuilderDTO {

    private String userId;
    private String password;
    private String name;
    private String nickname;
    private String profilePicture;
    private String email;
    private String phoneNumber;
}
