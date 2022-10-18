package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import com.jeeyulee.mongddang.member.dto.MemberLoginDTO;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.member.dto.MemberDTO;

public interface MemberService {
    public Boolean join(MemberJoinDTO memberJoinDTO);

    //public String login(MemberLoginDTO memberLoginDTO, LoginHistoryDTO loginHistoryDTO) throws UserNotFoundException;
    public String login(MemberLoginDTO memberLoginDTO) throws UserNotFoundException;
    public Boolean checkOverlap(String userId);
    public MemberDTO findById(String userId);
    public Boolean updateMember(MemberDTO memberDTO);

    public Boolean resign(String userId);
}

