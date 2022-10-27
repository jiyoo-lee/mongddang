package com.jeeyulee.mongddang.drawer.guestBook.service;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookBuilderDTO;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookDTO;
import com.jeeyulee.mongddang.drawer.guestBook.repository.GuestBookRepository;
import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestBookService{

    private final GuestBookRepository guestBookRepository;
    private final JwtService jwtService;

    @Override
    public Boolean createGuestBook(String userId, GuestBookDTO guestBookDTO) throws ResultException {

        String guestIdOnToken = jwtService.retrieveUserId();

        GuestBookBuilderDTO guestBookBuilderDTO = GuestBookBuilderDTO.builder()
                .drawerMemberId(userId)
                .guestId(guestIdOnToken)
                .contents(guestBookDTO.getContents()).build();

        return guestBookRepository.save(guestBookBuilderDTO) > 0;
    }
}
