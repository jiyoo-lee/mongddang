package com.jeeyulee.mongddang.folllowing.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowingRepository {

    @Insert("insert into social(member_id, follow_member_id) " +
            "value(#{userIdOnToken}, #{userId})")
    public Integer save(String userIdOnToken, String userId);

    @Delete("delete from social " +
            "where member_id = #{userIdOnToken} and follow_member_id = #{userId} ")
    public Integer delete(String userIdOnToken, String userId);
}
