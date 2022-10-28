package com.jeeyulee.mongddang.drawer.guestBook.comment.service;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentBuilderDTO;
import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentDTO;
import com.jeeyulee.mongddang.drawer.guestBook.comment.repository.GuestBookCommentRepository;
import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService{

    private final GuestBookCommentRepository guestBookCommentRepository;
    private final JwtService jwtService;

    @Override
    public Boolean createGuestBookComment(String userId, GuestBookCommentDTO guestBookCommentDTO) {

       if(jwtService.retrieveUserId().equals(userId)){

           GuestBookCommentBuilderDTO builderDTO = GuestBookCommentBuilderDTO.builder()
                                                .userId(userId)
                                                .guestBookId(guestBookCommentDTO.getGuestBookId())
                                                .contents(guestBookCommentDTO.getContents()).build();

           return guestBookCommentRepository.save(builderDTO) > 0 ;
       }
       throw new ResultException("접근이 불가한 사용자입니다. 다시 로그인해주세요.");
    }
}
