package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.vo.MemberVO;

public interface JwtService {

    public String createJwt(MemberVO memberVO);

    public Boolean validate(String token);
}
