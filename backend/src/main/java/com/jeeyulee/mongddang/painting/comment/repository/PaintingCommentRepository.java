package com.jeeyulee.mongddang.painting.comment.repository;

import com.jeeyulee.mongddang.painting.comment.domain.CommentDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentBuilderDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentUpdateDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface PaintingCommentRepository {

    @Insert("insert into comment(member_id, painting_id, contents, secret) " +
            "value( #{memberId}, #{paintingId}, #{contents}, #{secret})")
    public Integer save(PaintingCommentBuilderDTO paintingCommentBuilderDTO);


    @Select("select member_id, " +
            "           (select nickname from member where user_id = comment.member_id)nickname, " +
            "painting_id, contents, create_datetime " +
            "from comment where painting_id = #{paintingId} ")
    public List<CommentDTO> retrieveComments(Long paintingId);

    @Update("update comment " +
            "set contents = IFNULL(#{contents}, contents), " +
            "secret = IFNULL(#{secret}, secret) " +
            "where id = #{commentId}")
    public Integer update(PaintingCommentUpdateDTO paintingCommentUpdateDTO);

    @Delete("delete " +
            "from comment " +
            "where id = #{commentId}")
    public Integer delete(Long commentId);
}
