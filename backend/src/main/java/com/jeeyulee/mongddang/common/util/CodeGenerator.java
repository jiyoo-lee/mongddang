package com.jeeyulee.mongddang.common.util;

public class CodeGenerator {
    public static String generateRandom(int digit){
        Long unit = (long) Math.pow(10, digit);
        int code = (int)((Math.random() + 1) * unit);

        return String.valueOf(code).substring(1);
    }
}
