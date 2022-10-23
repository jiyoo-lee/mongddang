package com.jeeyulee.mongddang.drops.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DropsType {
    NORMAL("일반"),
    SECRET("비밀"),
    MONGDDANG("몽땅전");

    private String name;
}
