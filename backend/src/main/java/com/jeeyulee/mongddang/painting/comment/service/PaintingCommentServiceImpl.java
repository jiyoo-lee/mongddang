package com.jeeyulee.mongddang.painting.comment.service;

import com.jeeyulee.mongddang.common.result.ResultException;
import com.jeeyulee.mongddang.member.service.JwtService;
import com.jeeyulee.mongddang.painting.comment.domain.CommentDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentBuilderDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentUpdateDTO;
import com.jeeyulee.mongddang.painting.comment.repository.PaintingCommentRepository;
import com.jeeyulee.mongddang.painting.domain.PaintingUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaintingCommentServiceImpl implements PaintingCommentService {

    private final PaintingCommentRepository paintingCommentRepository;

    private final JwtService jwtService;


    @Override
    public Boolean save(PaintingCommentDTO paintingCommentDTO) {

        String userIdOnToken = jwtService.retrieveUserId();

        PaintingCommentBuilderDTO builderDTO = PaintingCommentBuilderDTO.builder()
                .memberId(userIdOnToken)
                .paintingId(paintingCommentDTO.getPaintingId())
                .contents(paintingCommentDTO.getContents())
                .secret(paintingCommentDTO.getSecret()).build();

        return paintingCommentRepository.save(builderDTO) > 0;
    }

    @Override
    public List<CommentDTO> retrieveComments(Long paintingId) {
        if(paintingId == null) {throw new ResultException("댓글 조회가 불가한 접근입니다.");}

            return paintingCommentRepository.retrieveComments(paintingId);
    }

    @Override
    public Boolean update(Long commentId, PaintingCommentDTO paintingCommentDTO) {
        PaintingCommentUpdateDTO updateDTO = PaintingCommentUpdateDTO.builder()
                .commentId(commentId)
                .contents(paintingCommentDTO.getContents())
                .secret(paintingCommentDTO.getSecret())
                .build();

        return paintingCommentRepository.update(updateDTO) > 0;
    }

    @Override
    public Boolean delete(Long commentId) {

        return paintingCommentRepository.delete(commentId) > 0;
    }


}
