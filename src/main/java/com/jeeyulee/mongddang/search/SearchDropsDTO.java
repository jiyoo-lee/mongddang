package com.jeeyulee.mongddang.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchDropsDTO {

    private String memberId;
    private String nickname;
    private String profileUrl;
    private String name;
    private String genre;
    private String paintingUrl;
}
