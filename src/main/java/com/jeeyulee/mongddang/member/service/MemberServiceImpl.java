package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.common.mail.MailDTO;
import com.jeeyulee.mongddang.common.mail.MailService;
import com.jeeyulee.mongddang.common.mail.MailServiceImpl;
import com.jeeyulee.mongddang.common.mail.MessageWords;
import com.jeeyulee.mongddang.member.domain.*;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    MailService mailService;

    @Override
    public Boolean join(MemberJoinDTO memberJoinDTO) {
        log.info("MemberServiceImpl join memberJoinDTO ===> {}", memberJoinDTO);
        return memberRepository.save(memberJoinDTO) > 0;
    }

    @Override
    public String login(MemberLoginDTO memberLoginDTO) throws UserNotFoundException {
        MemberLoginResponseDTO member = memberRepository.findByUserIdAndPassword(memberLoginDTO);
        Integer count = memberRepository.saveLogInHistory(memberLoginDTO);
        log.info("MemberServiceImpl login ===> {} ", member.getAdmin());

        if (member == null || count == null || count == 0) {
            throw new UserNotFoundException();
        }
        String jwt = jwtService.createJwt(member);
        memberRepository.updateToken(memberLoginDTO.getUserId(), jwt);
        return jwt;
    }


    @Override
    public Boolean checkOverlap(String userId) {
        return memberRepository.countById(userId) > 0;
    }

    @Override
    public MemberDTO findById(String userId) {
        MemberDTO member = memberRepository.findById(userId);

        if(member == null){
            throw new UserNotFoundException();
        }

        return member;
    }

    @Override
    public Boolean updateMember(MemberDTO memberDTO) {
        return memberRepository.update(memberDTO) > 0;
    }

    @Override
    public Boolean resign(String userId) {
        log.info("MemberServiceImpl resign userId ===> {}",userId);
        return memberRepository.resignMember(userId) > 0;
    }

    @Override
    public Boolean findByIdAndPhoneNumber(FindPasswordDTO findPasswordDTO) {

        return memberRepository.findByIdAndPhoneNumber(findPasswordDTO) > 0;
    }

    @Override
    public String findIdByPhoneNumber(String phoneNumber) {

        return memberRepository.findIdByPhoneNumber(phoneNumber);
    }
    @Override
    public void mailTest() {
        String contents = MessageWords.AUTH_CONTENTS.getValue() + "123";

        try {
            mailService.send(MailDTO.builder()
                    .to("gojeasuk2@naver.com")
                    .title(MessageWords.AUTH_TITLE.getValue())
                    .contents(contents)
                    .build());
        } catch (Exception e) {
            log.info("메일 발송 중 에러 발생 {}", e);
        }
    }
}
