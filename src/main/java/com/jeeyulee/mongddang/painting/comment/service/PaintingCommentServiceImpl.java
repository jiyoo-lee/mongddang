package com.jeeyulee.mongddang.painting.comment.service;

import com.jeeyulee.mongddang.member.service.JwtService;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentBuilderDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentDTO;
import com.jeeyulee.mongddang.painting.comment.repository.PaintingCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaintingCommentServiceImpl implements PaintingCommentService {

    private final PaintingCommentRepository paintingCommentRepository;

    private final JwtService jwtService;

    @Override
    public Boolean save(PaintingCommentDTO paintingCommentDTO) {

        String userIdOnToken = jwtService.retrieveUserId();
        int isSecret = paintingCommentDTO.getSecret() ? 1 : 0;

        PaintingCommentBuilderDTO builderDTO = PaintingCommentBuilderDTO.builder()
                .memberId(userIdOnToken)
                .paintingId(paintingCommentDTO.getPaintingId())
                .contents(paintingCommentDTO.getContents())
                .secret(isSecret).build();

        return paintingCommentRepository.save(builderDTO) > 0;
    }
}
