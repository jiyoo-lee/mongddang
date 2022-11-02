package com.jeeyulee.mongddang.artscenter.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ContestBuilderDTO {

    private String memberId;
    private String title;
    private String posterUrl;
    private String startDay;
    private String EndDay;
}
