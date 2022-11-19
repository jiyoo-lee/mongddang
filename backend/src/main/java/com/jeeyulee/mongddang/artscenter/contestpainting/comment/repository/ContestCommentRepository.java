package com.jeeyulee.mongddang.artscenter.contestpainting.comment.repository;

import com.jeeyulee.mongddang.artscenter.contestpainting.comment.domain.ContestCommentDTO;
import com.jeeyulee.mongddang.painting.comment.domain.CommentDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContestCommentRepository {

    @Insert("insert into contest_comment(contest_painting_id, member_id, contents) " +
            "value (#{contestPaintingId}, #{memberId}, #{contents})")
    public Integer saveComment(ContestCommentDTO contestCommentDTO);

    @Select("select CC.id commentId, CC.member_id memberId, CC.contents, CC.create_datetime createDatetime, " +
            "M.nickname, M.profile_picture profileUrl from contest_comment CC join member M " +
            "on CC.member_id = M.user_id where CC.contest_painting_id = #{paintingId}")
    public List<CommentDTO> retrieveContestComments(Long paintingId);

    @Delete("delete from contest_comment " +
            "where id = #{contestCommentId} and member_id = #{userIdOnToken}")
    public Integer deleteComment(String userIdOnToken, Long contestCommentId);
}
