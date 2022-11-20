package com.jeeyulee.mongddang.admin.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContestUpdateDTO {

    private String title;
    private String posterUrl;
    private String startDay;
    private String EndDay;
}
