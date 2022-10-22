package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.common.mail.MailSendException;
import com.jeeyulee.mongddang.member.domain.*;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;

public interface MemberService {
    public Boolean join(MemberJoinDTO memberJoinDTO);
    public String login(MemberLoginDTO memberLoginDTO) throws UserNotFoundException;
    public Boolean checkOverlap(String userId);
    public MemberDTO findById(String userId);
    public Boolean updateMember(MemberDTO memberDTO);
    public Boolean resign(String userId);
    public String findByIdAndEmail(FindPasswordDTO findPasswordDTO) throws MailSendException;
    public String findIdByEmail(String email);
    public String retrieveAuthNumber(String email) throws MailSendException;
    public Boolean updatePassword(PasswordUpdateDTO passwordUpdateDTO);
}

