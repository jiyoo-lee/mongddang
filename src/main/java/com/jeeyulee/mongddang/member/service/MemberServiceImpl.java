package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.common.mail.MailSendException;
import com.jeeyulee.mongddang.common.mail.MailDTO;
import com.jeeyulee.mongddang.common.mail.MailService;
import com.jeeyulee.mongddang.common.mail.MessageWords;
import com.jeeyulee.mongddang.common.util.CodeGenerator;
import com.jeeyulee.mongddang.member.domain.*;
import com.jeeyulee.mongddang.member.exception.UserNotFoundException;
import com.jeeyulee.mongddang.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

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

        String fileName = convertToUUID(memberJoinDTO.getUserId(), memberJoinDTO.getNickname());

        MemberJoinBuilderDTO memberJoinBuilderDTO = MemberJoinBuilderDTO.builder()
                .userId(memberJoinDTO.getUserId())
                .password(memberJoinDTO.getPassword())
                .name(memberJoinDTO.getName())
                .nickname(memberJoinDTO.getNickname())
                .profilePicture(fileName + memberJoinDTO.getExtension())
                .email(memberJoinDTO.getEmail())
                .address(memberJoinDTO.getAddress())
                .phoneNumber(memberJoinDTO.getPhoneNumber())
                .build();

        return memberRepository.save(memberJoinBuilderDTO) > 0;
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
    public Boolean resign(MemberDeleteDTO memberDeleteDTO) {
        log.info("MemberServiceImpl resign userId ===> {}",memberDeleteDTO.getUserId());
        return memberRepository.resignMember(memberDeleteDTO) > 0;
    }

    @Override
    public String findByIdAndEmail(FindPasswordDTO findPasswordDTO) throws MailSendException{

        String userId = memberRepository.findIdByEmail(findPasswordDTO.getEmail());

        if(userId == null || !userId.equals(findPasswordDTO.getUserId())){
            throw new UserNotFoundException();
        }

        String authNumber = CodeGenerator.generateRandom(6);

        mailService.send(MailDTO.builder()
                .to(findPasswordDTO.getEmail())
                .title(MessageWords.AUTH_TITLE.getValue())
                .contents(MessageWords.AUTH_CONTENTS.getValue() + authNumber)
                .build());

        return authNumber;
    }

    @Override
    public String findIdByEmail (String email) {

        return memberRepository.findIdByEmail(email);
    }

    @Override
    public String retrieveAuthNumber(String email) throws MailSendException{
        String userId = memberRepository.findIdByEmail(email);

        if (userId == null){
           throw new UserNotFoundException();
        }

        String authNumber = CodeGenerator.generateRandom(6);

        mailService.send(MailDTO.builder()
                    .to(email)
                    .title(MessageWords.AUTH_TITLE.getValue())
                    .contents(MessageWords.AUTH_CONTENTS.getValue() + authNumber)
                    .build());

        return authNumber;
    }

    @Override
    public Boolean updatePassword(PasswordUpdateDTO passwordUpdateDTO) {
        return memberRepository.updatePassword(passwordUpdateDTO) > 0;
    }

    private String convertToUUID(String userId, String userName){
        return UUID.randomUUID().toString() + "_" + userId + "_" + userName;
    }
}
