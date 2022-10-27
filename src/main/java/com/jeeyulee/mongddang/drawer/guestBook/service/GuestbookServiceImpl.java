package com.jeeyulee.mongddang.drawer.guestBook.service;

import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.drawer.guestBook.domain.*;
import com.jeeyulee.mongddang.drawer.guestBook.repository.GuestBookRepository;
import com.jeeyulee.mongddang.member.service.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Override
    public Boolean updateGuestBook(String userId, GuestBookUpdateDTO guestBookUpdateDTO) throws ResultException {

        if(jwtService.retrieveUserId().equals(guestBookUpdateDTO.getGuestId())) {
            GuestBookUpdateBuilderDTO builderDTO = GuestBookUpdateBuilderDTO.builder()
                    .drawerMemberId(userId)
                    .guestId(guestBookUpdateDTO.getGuestId())
                    .guestBookId(guestBookUpdateDTO.getGuestBookId())
                    .contents(guestBookUpdateDTO.getContents()).build();

            return guestBookRepository.update(builderDTO) > 0;
        }
        else{
            throw new ResultException();
        }
    }

    @Override
    public Boolean deleteGuestbook(Long guestBookId) {

        GuestBookDeleteDTO guestBookDeleteDTO = GuestBookDeleteDTO.builder()
                .guestId(jwtService.retrieveUserId())
                .guestBookId(guestBookId).build();
        return guestBookRepository.delete(guestBookDeleteDTO) > 0;
    }

}
