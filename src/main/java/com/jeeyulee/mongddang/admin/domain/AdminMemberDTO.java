package com.jeeyulee.mongddang.admin.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminMemberDTO {

    private String memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private String lastAccessDatetime;
}
