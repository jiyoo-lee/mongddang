package com.jeeyulee.mongddang.admin.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminPaintingDTO {

    private Long paintingId;
    private String paintingName;
    private String memberId;
    private String name;
    private String createDatetime;
}
