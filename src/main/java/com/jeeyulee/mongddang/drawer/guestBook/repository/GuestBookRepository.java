package com.jeeyulee.mongddang.drawer.guestBook.repository;

import com.jeeyulee.mongddang.drawer.guestBook.domain.GuestBookBuilderDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestBookRepository {

    @Insert("insert into guest_book( drawer_member_id, drawer_guest_id, contents ) " +
            "value (#{drawerMemberId}, #{guestId}, #{contents})")
    public Integer save(GuestBookBuilderDTO guestBookBuilderDTO);

}
