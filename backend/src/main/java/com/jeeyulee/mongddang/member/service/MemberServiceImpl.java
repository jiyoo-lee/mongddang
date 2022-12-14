package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.common.mail.MailSendException;
import com.jeeyulee.mongddang.common.mail.MailDTO;
import com.jeeyulee.mongddang.common.mail.MailService;
import com.jeeyulee.mongddang.common.mail.MessageWords;
import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.common.util.CodeGenerator;
import com.jeeyulee.mongddang.member.domain.*;
import com.jeeyulee.mongddang.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final MailService mailService;

    @Override
    public Boolean join(MemberJoinDTO memberJoinDTO) {
        log.info("MemberServiceImpl join memberJoinDTO ===> {}", memberJoinDTO);

        try {
            return memberRepository.save(memberJoinDTO) > 0;
        } catch (DuplicateKeyException e) {
            throw new ResultException("이미 존재하는 회원ID나 이메일입니다.");
        }
    }

    @Override
    @Transactional
    public LoginResponseDTO login(MemberLoginDTO memberLoginDTO) {

        try {
            MemberLoginResponseDTO member = memberRepository.findByUserIdAndPassword(memberLoginDTO);
            Integer count = memberRepository.saveLogInHistory(memberLoginDTO);
            log.info("MemberServiceImpl login ===> {} ", member);

            if (member == null || count == null || count == 0) {
                throw new ResultException("로그인에 실패했습니다.");
            }
            String jwt = jwtService.createJwt(member);
            memberRepository.updateToken(memberLoginDTO.getUserId(), jwt);

            return new LoginResponseDTO(jwt, member.getAdmin());
        } catch(ResultException e) {
            throw e;
        } catch (Exception e){
            e.printStackTrace();
            throw new ResultException("아이디와 패스워드를 확인해주세요");
        }
    }
    @Override
    public Boolean checkOverlap(String userId) {
        return memberRepository.countById(userId) > 0;
    }

    @Override
    public MemberDTO findById(String userId) {
        MemberDTO member = memberRepository.findById(userId);

        if(member == null){
            throw new ResultException("불가능한 접근입니다.");
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
    @Transactional
    public String findByIdAndEmail(FindPasswordDTO findPasswordDTO){

        String userId = memberRepository.findIdByEmail(findPasswordDTO.getEmail());

        log.info("findByIdAndEmail userId ===> {}",userId);
        if(userId == null || !userId.equals(findPasswordDTO.getUserId())){
            throw new ResultException("사용자를 찾을 수 없습니다. ");
        }

        String authNumber = CodeGenerator.generateRandom(6);

        try {
            mailService.send(MailDTO.builder()
                    .to(findPasswordDTO.getEmail())
                    .title(MessageWords.AUTH_TITLE.getValue())
                    .contents(MessageWords.AUTH_CONTENTS.getValue() + authNumber)
                    .build());
        }catch (MailSendException e){
            throw new ResultException("메일 전송에 실패하였습니다.");
        }
        return authNumber;
    }

    @Override
    public String findIdByEmail (String email) {
        log.info("findIdByEmail ===> {}", email);
        return memberRepository.findIdByEmail(email);
    }

    @Override
    public String retrieveAuthNumber(String email) {
        String userId = memberRepository.findIdByEmail(email);

        if (userId == null){
           throw new ResultException("사용자를 찾을 수 없습니다.");
        }

        String authNumber = CodeGenerator.generateRandom(6);

        try {
            mailService.send(MailDTO.builder()
                    .to(email)
                    .title(MessageWords.AUTH_TITLE.getValue())
                    .contents(MessageWords.AUTH_CONTENTS.getValue() + authNumber)
                    .build());
        } catch (Exception e) {
            throw new ResultException("메일 발송 실패");
        }

        return authNumber;
    }

    @Override
    public Boolean updatePassword(PasswordUpdateDTO passwordUpdateDTO) {
        return memberRepository.updatePassword(passwordUpdateDTO) > 0;
    }

    @Override
    public Boolean uploadProfilePicture(String userId, String paintingUrl) {

        int update = memberRepository.updateProfilePicture(userId, paintingUrl);
        System.out.println(userId);
        System.out.println(paintingUrl);
        if (update  < 1) {
            throw new ResultException("프로필 사진 업로드 중 오류가 발생했습니다.");
        }
        return update > 0;
    }

    @Override
    public Boolean deleteProfilePicture(String userId) {
        return memberRepository.deleteProfilePicture(userId) > 0;
    }

    private String getFileName(String userId, String extenstion){
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return UUID.randomUUID() + "_" + userId + today + extenstion;
    }
}
