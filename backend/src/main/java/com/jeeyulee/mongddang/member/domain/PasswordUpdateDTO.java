package com.jeeyulee.mongddang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordUpdateDTO {
    private String userId;
    private String password;
}
