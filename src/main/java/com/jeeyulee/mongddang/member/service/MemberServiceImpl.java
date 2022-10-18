package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.dto.*;
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

    @Override
    public Boolean join(MemberJoinDTO memberJoinDTO) {
        log.info("MemberServiceImpl join memberJoinDTO ===> {}", memberJoinDTO);
        return memberRepository.save(memberJoinDTO) > 0;
    }

    @Override
    public String login(MemberLoginDTO memberLoginDTO) throws UserNotFoundException {
        MemberLoginResponseDTO member = memberRepository.findByUserIdAndPassword(memberLoginDTO);
        //int count = memberRepository.saveLogInHistory(loginHistoryDTO);

        if (member == null) {
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


}
