package com.jeeyulee.mongddang.member.service;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.member.domain.MemberJoinDTO;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @DisplayName("같은 ID가 있는 경우 회원가입 실패")
    @Transactional
    @Test(expected = ResultException.class)
    public void idOverlapFailTest() {
        // given
        MemberJoinDTO member = new MemberJoinDTO();
        member.setUserId("mong");
        member.setPassword("1234");
        member.setEmail("abc@mongddang.com");
        member.setNickname("몽당연필");
        member.setName("이지유");
        member.setPhoneNumber("01012345678");

        // when
        boolean success1 = memberService.join(member);

        // then (ResultException 발생)
        boolean success2 = memberService.join(member);
    }
}