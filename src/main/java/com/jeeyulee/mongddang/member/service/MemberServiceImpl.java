package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.member.dto.MemberJoinDTO;
import com.jeeyulee.mongddang.member.dto.MemberLoginDTO;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.member.repository.MemberRepository;
import com.jeeyulee.mongddang.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        MemberVO member = memberRepository.findByUserIdAndPassword(memberLoginDTO);

        if (member == null) {
            throw new UserNotFoundException();
        }

        return jwtService.createJwt(member);
    }

    @Override
    public Boolean checkOverlap(String userId) {
        return memberRepository.countById(userId) > 0;
    }

    @Override
    public MemberVO findById(String userId) {
        MemberVO member = memberRepository.findById(userId);

        if(member == null){
            throw new UserNotFoundException();
        }

        return member;
    }

    @Override
    public Boolean updateMember(MemberVO memberVO) {
        return memberRepository.update(memberVO) > 0;
    }


}
