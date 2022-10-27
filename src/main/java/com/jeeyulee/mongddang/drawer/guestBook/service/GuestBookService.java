package com.jeeyulee.mongddang.drawer.guestBook.service;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookDTO;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookUpdateDTO;

public interface GuestBookService {

    public Boolean createGuestBook(String userId, GuestBookDTO guestBookDTO) throws ResultException;
    public Boolean updateGuestBook(String userId, GuestBookUpdateDTO guestBookUpdateDTO) throws ResultException;
    public Boolean deleteGuestbook(Long guestBookId);
}
