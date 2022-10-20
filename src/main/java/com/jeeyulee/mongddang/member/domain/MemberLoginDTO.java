package com.jeeyulee.mongddang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDTO {
    private String userId;
    private String password;
    private String accessIp;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
