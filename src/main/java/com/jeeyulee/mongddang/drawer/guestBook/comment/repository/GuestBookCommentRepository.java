package com.jeeyulee.mongddang.drawer.guestBook.comment.repository;

import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentBuilderDTO;
import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentUpdateDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GuestBookCommentRepository {

    @Select("select drawer_member_id from guest_book " +
            "where drawer_member_id = #{userId} ")
    public String retrieveUserIdByToken(String userId);

    @Insert("insert into guest_book_comment(guest_book_id, contents) " +
            "value (#{guestBookId}, #{contents})")
    public Integer save(GuestBookCommentBuilderDTO guestBookCommentBuilderDTO);

    @Update("update guest_book_comment " +
            "set contents = #{contents} " +
            "where id = #{commentId}")
    public Integer update(GuestBookCommentUpdateDTO guestBookCommentUpdateDTO);

    @Delete("delete " +
            "from guest_book_comment " +
            "where id = #{commentId}")
    public Integer delete(Long commentId);
}
