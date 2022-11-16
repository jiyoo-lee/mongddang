package com.jeeyulee.mongddang.drawer.guestBook.service;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.drawer.guestBook.domain.*;
import com.jeeyulee.mongddang.drawer.guestBook.repository.GuestBookRepository;
import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
            throw new ResultException("잘못된 접근입니다. ");
    }

    @Override
    public Boolean deleteGuestbook(Long guestBookId) {

        GuestBookDeleteDTO guestBookDeleteDTO = GuestBookDeleteDTO.builder()
                .guestId(jwtService.retrieveUserId())
                .guestBookId(guestBookId).build();
        return guestBookRepository.delete(guestBookDeleteDTO) > 0;
    }

    @Override
    public List<GuestBookResponseDTO> retrieveGuestBook(String userId) {
        List<GuestBookAndCommentDTO> guestBookAndCommentDTOs = guestBookRepository.findByUserId(userId);
        return guestBookAndCommentDTOs.stream()
                                      .map(this::convertToResponse)
                                      .collect(Collectors.toList());
    }

    private GuestBookResponseDTO convertToResponse(GuestBookAndCommentDTO guestBookAndCommentDTO) {
        return GuestBookResponseDTO.builder()
                .guestBookId(guestBookAndCommentDTO.getGuestBookId())
                .guestId(guestBookAndCommentDTO.getGuestId())
                .contents(guestBookAndCommentDTO.getGuestBookContents())
                .guestBookCreateDatetime(guestBookAndCommentDTO.getGuestBookCreateDatetime())
                .comment(GuestBookResponseCommentDTO.builder()
                        .commentId(guestBookAndCommentDTO.getCommentId())
                        .contents(guestBookAndCommentDTO.getCommentContents())
                        .commentCreateDatetime(guestBookAndCommentDTO.getCommentCreateDatetime())
                        .build())
                .build();
    }

}
