package com.jeeyulee.mongddang.common.mail;

import lombok.Getter;

@Getter
public enum MessageWords {
    AUTH_TITLE("인증코드 발송"),

    AUTH_CONTENTS("안녕하세요 몽땅입니다. \n인증코드는 아래와 같습니다. \n   인증번호 : ");
    private String value;

    private MessageWords(String value) {
        this.value = value;
    }
}
