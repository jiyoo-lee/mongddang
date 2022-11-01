package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.common.mail.MailSendException;
import com.jeeyulee.mongddang.member.domain.*;

public interface MemberService {
    public Boolean join(MemberJoinDTO memberJoinDTO);
    public String login(MemberLoginDTO memberLoginDTO);
    public Boolean checkOverlap(String userId);
    public MemberDTO findById(String userId);
    public Boolean updateMember(MemberDTO memberDTO);
    public Boolean resign(MemberDeleteDTO memberDeleteDTO);
    public String findByIdAndEmail(FindPasswordDTO findPasswordDTO);
    public String findIdByEmail(String email);
    public String retrieveAuthNumber(String email);
    public Boolean updatePassword(PasswordUpdateDTO passwordUpdateDTO);
}

