package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.domain.FindPasswordDTO;
import com.jeeyulee.mongddang.member.domain.MemberJoinDTO;
import com.jeeyulee.mongddang.member.domain.MemberLoginDTO;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.member.domain.MemberDTO;

public interface MemberService {
    public Boolean join(MemberJoinDTO memberJoinDTO);
    public String login(MemberLoginDTO memberLoginDTO) throws UserNotFoundException;
    public Boolean checkOverlap(String userId);
    public MemberDTO findById(String userId);
    public Boolean updateMember(MemberDTO memberDTO);
    public Boolean resign(String userId);
    public Boolean findByIdAndPhoneNumber(FindPasswordDTO findPasswordDTO);
    public String findIdByPhoneNumber(String phoneNumber);

    public void mailTest();
}

