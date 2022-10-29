package com.jeeyulee.mongddang.member.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDTO {
    @NotBlank(message = "회원 ID는 필수 값입니다.")
    private String userId;

    private String password;

    @NotBlank(message = "이름은 필수 값입니다.")
    private String name;

    @NotBlank(message = "닉네임은 필수 값입니다.")
    private String nickname;

    private String extension;

    @Email(message = "이메일 양식을 지켜주세요.")
    @NotBlank(message = "이메일은 필수 값입니다.")
    private String email;

    @NotBlank(message = "휴대폰 번호는 필수 값입니다.")
    private String phoneNumber;

    @NotBlank(message = "주소는 필수 값입니다.")
    private String address;
}
