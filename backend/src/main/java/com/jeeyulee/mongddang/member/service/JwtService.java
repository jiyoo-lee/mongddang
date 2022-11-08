package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.domain.MemberLoginResponseDTO;

public interface JwtService {

    public String createJwt(MemberLoginResponseDTO memberLoginResponseDTO);

    public Boolean validate(String token);

    public String retrieveUserId();
}
