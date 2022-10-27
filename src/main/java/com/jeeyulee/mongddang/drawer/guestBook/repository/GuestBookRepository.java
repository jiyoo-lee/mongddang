package com.jeeyulee.mongddang.drawer.guestBook.repository;

import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookBuilderDTO;
import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookUpdateBuilderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GuestBookRepository {

    @Insert("insert into guest_book( drawer_member_id, drawer_guest_id, contents ) " +
            "value (#{drawerMemberId}, #{guestId}, #{contents})")
    public Integer save(GuestBookBuilderDTO guestBookBuilderDTO);

    @Update("update guest_book " +
            "set contents = #{contents} " +
            "where id = #{GuestBookId} ")
    public Integer update(GuestBookUpdateBuilderDTO builderDTO);
}
