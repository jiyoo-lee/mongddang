package com.jeeyulee.mongddang.artscenter.contestpainting.comment.repository;

import com.jeeyulee.mongddang.artscenter.contestpainting.comment.domain.ContestCommentDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestCommentRepository {

    @Insert("insert into contest_comment(contest_painting_id, member_id, contents) " +
            "value (#{contestPaintingId}, #{memberId}, #{contents})")
    public Integer saveComment(ContestCommentDTO contestCommentDTO);

    @Delete("delete from contest_comment " +
            "where id = #{contestCommentId} and member_id = #{userIdOnToken}")
    public Integer deleteComment(String userIdOnToken, Long contestCommentId);
}
