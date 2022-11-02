package com.jeeyulee.mongddang.artscenter.contestpainting.comment.service;


import com.jeeyulee.mongddang.artscenter.contestpainting.comment.domain.ContestCommentDTO;
import com.jeeyulee.mongddang.artscenter.contestpainting.comment.repository.ContestCommentRepository;
import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.member.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContestCommentServiceImpl implements ContestCommentService{

    private final ContestCommentRepository contestCommentRepository;
    private final JwtService jwtService;

    @Override
    public Boolean saveComment(ContestCommentDTO contestCommentDTO) {


        return contestCommentRepository.saveComment(contestCommentDTO) > 0;
    }

    @Override
    public Boolean deleteComment(Long contestCommentId) {

        try {
            String userIdOnToken = jwtService.retrieveUserId();
            return contestCommentRepository.deleteComment(userIdOnToken, contestCommentId) > 0;
        }catch (Exception e){
            throw new ResultException("불가능한 접근입니다.");
        }
    }
}
