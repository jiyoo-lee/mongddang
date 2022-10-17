package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import com.jeeyulee.mongddang.member.dto.MemberLoginDTO;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.member.vo.MemberVO;

import java.util.List;

public interface MemberService {
    public Boolean join(MemberJoinDTO memberJoinDTO);

    public String login(MemberLoginDTO memberLoginDTO) throws UserNotFoundException;
    public Boolean checkOverlap(String userId);
    public MemberVO findById(String userId);
    public Boolean updateMember(MemberVO memberVO);
}

