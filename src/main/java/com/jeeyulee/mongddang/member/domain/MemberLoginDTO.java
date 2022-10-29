package com.jeeyulee.mongddang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDTO {
    @NotBlank(message = "ID는 필수 값입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    private String password;

    @NotBlank(message = "IP는 필수 값입니다.")
    private String accessIp;

    @NotNull(message = "위도는 필수 값입니다.")
    private BigDecimal latitude;

    @NotNull(message = "경도는 필수 값입니다.")
    private BigDecimal longitude;
}
