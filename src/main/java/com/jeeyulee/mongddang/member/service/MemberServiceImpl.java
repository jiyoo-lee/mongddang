package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import com.jeeyulee.mongddang.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;
    @Override
    public Boolean join(MemberJoinDTO memberJoinDTO) {
        log.info("MemberServiceImpl join memberJoinDTO ===> {}", memberJoinDTO);
        return memberRepository.save(memberJoinDTO) > 0;
    }
}
