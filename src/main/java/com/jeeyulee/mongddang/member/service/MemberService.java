package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import com.jeeyulee.mongddang.member.dto.MemberLoginDTO;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;

import java.util.List;

public interface MemberService {
    public Boolean join(MemberJoinDTO memberJoinDTO);

    public String login(MemberLoginDTO memberLoginDTO) throws UserNotFoundException;
}

