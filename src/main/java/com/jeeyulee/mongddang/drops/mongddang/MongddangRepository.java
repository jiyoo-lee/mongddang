package com.jeeyulee.mongddang.drops.mongddang;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MongddangRepository {

    @Insert("insert into drops_mongddang (drops_id, member_id) " +
            "value (#{dropsId}, #{userIdOnToken})")
    public Integer save(Long dropsId, String userIdOnToken);

    @Delete("delete from drops_mongddang " +
            "where drops_id = #{dropsId} and member_id = #{userIdOnToken}")
    public Integer delete(Long dropsId, String userIdOnToken);
}
