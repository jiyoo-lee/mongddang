package com.jeeyulee.mongddang.drawer.guestBook.service;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookDTO;

public interface GuestBookService {

    public Boolean createGuestBook(String userId, GuestBookDTO guestBookDTO) throws ResultException;
}
