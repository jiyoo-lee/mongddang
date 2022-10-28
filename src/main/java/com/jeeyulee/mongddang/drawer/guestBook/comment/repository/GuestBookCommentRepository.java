package com.jeeyulee.mongddang.drawer.guestBook.comment.repository;

import com.jeeyulee.mongddang.drawer.guestBook.comment.domain.GuestBookCommentBuilderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GuestBookCommentRepository {

    @Select("select drawer_member_id from guest_book " +
            "where drawer_member_id = #{userId} ")
    public String retrieveUserIdByToken(String userId);

    @Insert("insert into guest_book_comment(guest_book_id, contents) " +
            "value (#{guestBookId}, #{contents})")
    public Integer save(GuestBookCommentBuilderDTO guestBookCommentBuilderDTO);
}
