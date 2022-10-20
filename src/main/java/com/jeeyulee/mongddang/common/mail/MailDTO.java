package com.jeeyulee.mongddang.common.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MailDTO {
    private String to;
    private String title;
    private String contents;
}
