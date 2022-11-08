package com.jeeyulee.mongddang.drawer.guestBook.service;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookDTO;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookResponseDTO;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookUpdateDTO;

import java.util.List;

public interface GuestBookService {

    Boolean createGuestBook(String userId, GuestBookDTO guestBookDTO) throws ResultException;
    Boolean updateGuestBook(String userId, GuestBookUpdateDTO guestBookUpdateDTO) throws ResultException;
    Boolean deleteGuestbook(Long guestBookId);
    List<GuestBookResponseDTO> retrieveGuestBook(String userId);
}
