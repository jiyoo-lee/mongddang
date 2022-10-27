package com.jeeyulee.mongddang.drawer.guestBook.controller;


import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookDTO;
import com.jeeyulee.mongddang.drawer.guestBook.service.GuestBookService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/drawer")
public class GuestBookController {

    public final GuestBookService guestBookService;

    @ApiOperation(value = "방명록 등록 API", notes = "서랍장 방명록 등록 API")
    @PostMapping("/{userId}/guestbook")
    public ResponseEntity<ResultDTO> createGuestBook(@PathVariable String userId, GuestBookDTO guestBookDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(guestBookService.createGuestBook(userId, guestBookDTO));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
