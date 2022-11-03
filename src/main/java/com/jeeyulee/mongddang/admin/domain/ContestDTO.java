package com.jeeyulee.mongddang.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContestDTO {

    private String memberId;
    private String title;
    private String extension;
    private String startDay;
    private String EndDay;
}
