package com.jeeyulee.mongddang.member.dto;


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
