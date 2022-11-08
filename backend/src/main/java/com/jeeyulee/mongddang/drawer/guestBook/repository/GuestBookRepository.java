package com.jeeyulee.mongddang.drawer.guestBook.repository;

import com.jeeyulee.mongddang.drawer.guestBook.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GuestBookRepository {

    @Insert("insert into guest_book( drawer_member_id, drawer_guest_id, contents ) " +
            "value (#{drawerMemberId}, #{guestId}, #{contents})")
    public Integer save(GuestBookBuilderDTO guestBookBuilderDTO);

    @Update("update guest_book " +
            "set contents = #{contents} " +
            "where id = #{guestBookId} and drawer_guest_id = #{guestId}")
    public Integer update(GuestBookUpdateBuilderDTO builderDTO);

    @Delete("delete from guest_book " +
            "where id = #{guestBookId} " +
            "and drawer_guest_id = #{guestId}")
    public Integer delete(GuestBookDeleteDTO guestBookDeleteDTO);

    @Select("select GB.id guestBookId, " +
            "       GB.drawer_guest_id guestId, " +
            "       GB.contents guestBookContents, " +
            "       GB.create_datetime guestBookCreateDatetime, " +
            "       GBC.id commentId, " +
            "       GBC.contents commentContents, " +
            "       GBC.create_datetime contentsCreateDatetime " +
            "from (select id, drawer_guest_id, contents, create_datetime " +
            "      from guest_book " +
            "      where drawer_member_id = #{userId}) GB left outer join guest_book_comment GBC " +
            "on GB.id = GBC.guest_book_id")
    public List<GuestBookAndCommentDTO> findByUserId(String userId);
}
