package com.jeeyulee.mongddang.painting.comment.repository;

import com.jeeyulee.mongddang.painting.comment.domain.CommentDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentBuilderDTO;
import com.jeeyulee.mongddang.painting.comment.domain.PaintingCommentUpdateDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface PaintingCommentRepository {

    @Insert("insert into comment(member_id, painting_id, contents) " +
            "value( #{memberId}, #{paintingId}, #{contents})")
    public Integer save(PaintingCommentBuilderDTO paintingCommentBuilderDTO);


    @Select("select id commentId, member_id, " +
            "           (select nickname from member where user_id = comment.member_id)nickname, " +
            "           (select profile_picture from member where user_id = comment.member_id)profileUrl, " +
            "           (select description from painting where id = comment.painting_id)description, " +
            "painting_id, contents, create_datetime " +
            "from comment where painting_id = #{paintingId} ")
    public List<CommentDTO> retrieveComments(Long paintingId);

    @Update("update comment " +
            "set contents = IFNULL(#{contents}, contents) " +
            "where id = #{commentId}")
    public Integer update(PaintingCommentUpdateDTO paintingCommentUpdateDTO);

    @Delete("delete " +
            "from comment " +
            "where id = #{commentId}")
    public Integer delete(Long commentId);
}
