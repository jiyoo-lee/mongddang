package com.jeeyulee.mongddang.painting.comment.repository;

import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentBuilderDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaintingCommentRepository {

    @Insert("insert into comment(member_id, painting_id, contents, secret) " +
            "value( #{memberId}, #{paintingId}, #{contents}, #{secret})")
    public Integer save(PaintingCommentBuilderDTO paintingCommentBuilderDTO);

}
