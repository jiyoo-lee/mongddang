package com.jeeyulee.mongddang.drawer.guestBook.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GuestBookUpdateDTO {
    private String GuestId;
    private Long GuestBookId;
    private String contents;
}
