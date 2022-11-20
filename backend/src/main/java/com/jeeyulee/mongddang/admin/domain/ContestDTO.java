package com.jeeyulee.mongddang.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContestDTO {

    private String contestId;
    private String memberId;
    private String title;
    private String posterUrl;
    private Boolean deadline;
    private String startDay;
    private String EndDay;
    private String createDatetime;
}
