package com.jeeyulee.mongddang.drawer.guestBook.controller;


import com.jeeyulee.mongddang.common.result.ResultDTO;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookDTO;
import com.jeeyulee.mongddang.drawer.guestBook.service.GuestBookService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value="", notes = "")
    @PutMapping("/{userId})/guestbook/{guesetbookId}")
    public ResponseEntity<ResultDTO> updateGuestBook(@PathVariable String userId,
                                                     @PathVariable Long guestBookId,
                                                     GuestBookDTO guestBookDTO){
        ResultDTO result = new ResultDTO();
        result.setSuccess(guestBookService.updateGuestBook(userId, guestBookId,guestBookDTO));

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
