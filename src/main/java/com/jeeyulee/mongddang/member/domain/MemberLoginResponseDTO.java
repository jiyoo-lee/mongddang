package com.jeeyulee.mongddang.member.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginResponseDTO {
    private String userId;
    private Boolean admin;
}
