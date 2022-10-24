package com.jeeyulee.mongddang.painting.comment.repository;

import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentBuilderDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentUpdateDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PaintingCommentRepository {

    @Insert("insert into comment(member_id, painting_id, contents, secret) " +
            "value( #{memberId}, #{paintingId}, #{contents}, #{secret})")
    public Integer save(PaintingCommentBuilderDTO paintingCommentBuilderDTO);

    @Update("update comment " +
            "set contents = IFNULL(#{contents}, contents), " +
            "secret = IFNULL(#{secret}, secret) " +
            "where id = #{commentId}")
    public Integer update(PaintingCommentUpdateDTO paintingCommentUpdateDTO);
}
