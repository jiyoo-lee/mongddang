package com.jeeyulee.mongddang.drawer.guestBook.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GuestBookUpdateBuilderDTO {
    private String drawerMemberId;
    private String guestId;
    private Long guestBookId;
    private String contents;
}
